package com.mofoluwashokayode.popularmovies1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.mofoluwashokayode.popularmovies1.parser.Movies;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    private final static String BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String SIZE = "w342";
    private static final String MOVIE = "MOVIE";
    private ImageView moviePoster;
    private TextView originalTitle;
    private TextView releaseDate;
    private TextView userRating;
    private TextView plotSynopsis;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Movies movies = getActivity().getIntent().getParcelableExtra(MOVIE);

        originalTitle = view.findViewById(R.id.originalTitle);
        moviePoster = view.findViewById(R.id.movie_poster);
        releaseDate = view.findViewById(R.id.release_date);
        userRating = view.findViewById(R.id.user_rating);
        plotSynopsis = view.findViewById(R.id.plot_synopsis);

        if (movies != null) {
            System.out.println(movies);
            originalTitle.setText(movies.getTitle());
            loadImage(movies.getPosterPath());
            releaseDate.setText(String.format("%.4s", movies.getReleaseDate()));
            userRating.setText(String.format("%s/10", movies.getVoteAverage()));
            plotSynopsis.setText(movies.getPlotSynopsis());
        }

        return view;
    }


    private void loadImage(String path) {
        String urlBuilder = BASE_URL +
                SIZE +
                path;

        Picasso.with(getContext())
                .load(urlBuilder)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(moviePoster);
    }
}
