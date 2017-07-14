package com.example.suadahaji.themovieapplication.mvp_movie_detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.suadahaji.themovieapplication.R;
import com.example.suadahaji.themovieapplication.api.ApiClient;
import com.example.suadahaji.themovieapplication.models.Movie;
import com.example.suadahaji.themovieapplication.models.Review;
import com.example.suadahaji.themovieapplication.models.Video;
import com.example.suadahaji.themovieapplication.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailsView, View.OnClickListener {

    private MovieDetailsPresenterImpl presenter;

    @BindView(R.id.movie_poster)
    ImageView poster;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.movie_name)
    TextView title;
    @BindView(R.id.movie_year)
    TextView releaseDate;
    @BindView(R.id.movie_rating)
    TextView rating;
    @BindView(R.id.movie_description)
    TextView overview;
    @BindView(R.id.trailers_label)
    TextView label;
    @BindView(R.id.trailers)
    LinearLayout videos;
    @BindView(R.id.trailers_container)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.reviews_label)
    TextView reviews;
    @BindView(R.id.reviews)
    LinearLayout reviewsContainer;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_error_state)
    TextView errorTextView;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        movie = extras.getParcelable(Constants.MOVIE);
        String posterUrl = Constants.POSTER_PATH + movie.getPosterPath();

        setToolbar();

        ApiClient apiClient = new ApiClient();

        presenter = new MovieDetailsPresenterImpl(apiClient, movie, Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.setDetailsView(this);

        Glide.with(this).load(posterUrl).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(poster) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);

                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch textSwatch = palette.getDarkVibrantSwatch();
                                if (textSwatch != null) {
                                    collapsingToolbar.setContentScrimColor(textSwatch.getRgb());
                                    collapsingToolbar.setStatusBarScrimColor(textSwatch.getRgb());
                                } else {
                                    textSwatch = palette.getDarkMutedSwatch();
                                    collapsingToolbar.setContentScrimColor(textSwatch.getRgb());
                                    collapsingToolbar.setStatusBarScrimColor(textSwatch.getRgb());
                                }
                            }
                        });
                    }
                });

        title.setText(movie.getTitle());
        releaseDate.setText(String.format(getString(R.string.release_date), movie.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        overview.setText(movie.getOverview());
    }

    private void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
        collapsingToolbar.setTitle(movie.getTitle());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }
        }
    }

    @Override
    public void showVideos(ArrayList<Video> videos) {
        if (videos.isEmpty()) {
            label.setVisibility(View.GONE);
            this.videos.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);

        } else {
            label.setVisibility(View.VISIBLE);
            this.videos.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);

            this.videos.removeAllViews();
            LayoutInflater inflater = this.getLayoutInflater();
            for (Video video : videos) {
                View thumbContainer = inflater.inflate(R.layout.video, this.videos, false);
                ImageView thumbView = (ImageView) thumbContainer.findViewById( R.id.video_thumb);
                thumbView.setTag(Constants.YOUTUBE_VIDEO_URL + video.getKey());
                thumbView.requestLayout();
                thumbView.setOnClickListener(this);
                Picasso.with(this).load(Constants.YOUTUBE_THUMBNAIL_URL + video.getKey() + "/0.jpg")
                        .resizeDimen(R.dimen.margin_150, R.dimen.video_height)
                        .centerCrop()
                        .placeholder(R.color.colorPrimary)
                        .into(thumbView);
                this.videos.addView(thumbContainer);
            }
        }

    }

    @Override
    public void showReviews(ArrayList<Review> reviews) {
        if (reviews.isEmpty()) {
            this.reviews.setVisibility(View.GONE);
            reviewsContainer.setVisibility(View.GONE);
        } else {
            this.reviews.setVisibility(View.VISIBLE);
            reviewsContainer.setVisibility(View.VISIBLE);

            reviewsContainer.removeAllViews();
            LayoutInflater inflater = this.getLayoutInflater();
            for (Review review : reviews) {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.review, reviewsContainer, false);
                TextView reviewAuthor = ButterKnife.findById(reviewContainer, R.id.review_author);
                TextView reviewContent = ButterKnife.findById(reviewContainer, R.id.review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                reviewsContainer.addView(reviewContainer);
            }
        }

    }

    @Override
    public void showDetailErrorMessage() {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.video_thumb:
                onThumbnailClick(view);
                break;
            case R.id.review_content:
                onReviewClick((TextView) view);
                break;
            default:
                break;
        }
    }

    private void onThumbnailClick(View view)
    {
        String videoUrl = (String) view.getTag();
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(playVideoIntent);
    }


    private void onReviewClick(TextView view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (view.getMaxLines() == 5)
            {
                view.setMaxLines(500);
            } else
            {
                view.setMaxLines(5);
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.destroy();
    }
}
