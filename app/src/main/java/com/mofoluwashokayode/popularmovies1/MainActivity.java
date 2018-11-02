package com.mofoluwashokayode.popularmovies1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.AdapterView;

import java.util.Collections;

import com.mofoluwashokayode.popularmovies1.parser.Movies;


public class MainActivity extends AppCompatActivity {

    private static final String MOVIE = "MOVIE";
    private static final String LIST_OF_MOVIES = "LIST_OF_MOVIES";
    private ArrayList<Movies> listOfMovies;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null || !savedInstanceState.containsKey(LIST_OF_MOVIES)) {
            listOfMovies = new ArrayList<>();
        } else {
            listOfMovies = savedInstanceState.getParcelableArrayList(LIST_OF_MOVIES);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView moviesGrid = findViewById(R.id.movies_Grid);
        movieAdapter = new MovieAdapter(this, listOfMovies);

        moviesGrid.setAdapter(movieAdapter);
        moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movies movie = movieAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra(MOVIE, movie);
                startActivity(intent);
            }
        });

        getMovies();


    }


    private void getMovies() {

        FetchMovieTask movTask = new FetchMovieTask(new MyCallback() {
            @Override
            public void updateAdapter(Movies[] movies) {

                if (movies != null) {
                    movieAdapter.clear();
                    Collections.addAll(listOfMovies, movies);
                    movieAdapter.notifyDataSetChanged();
                }

            }


        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(getString(R.string.sort_key), getString(R.string.sort_popular));
        movTask.execute(sortOrder);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LIST_OF_MOVIES, listOfMovies);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onStart() {
        super.onStart();

        getMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



