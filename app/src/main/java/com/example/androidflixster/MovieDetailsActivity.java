package com.example.androidflixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.androidflixster.R;
import com.example.androidflixster.models.Movie;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    TextView tvdTitle;
    TextView tvdOverview;
    TextView tvdDate;
    RatingBar rbVoteAverage;
    ImageView ivdPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        tvdTitle = (TextView) findViewById(R.id.tvdTitle);
        tvdOverview = (TextView) findViewById(R.id.tvdOverview);
        tvdDate = (TextView) findViewById(R.id.tvdDate);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        ivdPoster = (ImageView) findViewById(R.id.ivdPoster);

        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details from '%s'", movie.getTitle()));

        tvdTitle.setText(movie.getTitle());
        tvdOverview.setText(movie.getOverview());
        tvdDate.setText(movie.getDate());

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        String imageUrl = movie.getBackdropPath();
        int placeholder = R.drawable.placeholder_backdrop;
        Glide.with(this)
                .load(imageUrl)
                .placeholder(placeholder)
                .transform(new RoundedCorners(30))
                .into(ivdPoster);
    }
}