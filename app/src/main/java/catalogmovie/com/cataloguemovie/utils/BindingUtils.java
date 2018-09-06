package catalogmovie.com.cataloguemovie.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.ui.ItemMovieSearch.MovieSearchAdapter;
import catalogmovie.com.cataloguemovie.ui.NowPlaying.NowPlayingMovieAdapter;
import catalogmovie.com.cataloguemovie.ui.UpComing.UpComingMovieAdapter;


public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    @BindingAdapter({"movieSearchAdapter"})
    public static void addMovieSearchAdapter(RecyclerView recyclerView,
                                     ArrayList<Movie> movies) {
        MovieSearchAdapter adapter = (MovieSearchAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(movies);
        }
    }

    @BindingAdapter({"movieNowPlayingAdapter"})
    public static void addMovieNowPlayingAdapter(RecyclerView recyclerView,
                                                 ArrayList<Movie> movies) {
        NowPlayingMovieAdapter adapter = (NowPlayingMovieAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(movies);
        }
    }

    @BindingAdapter({"movieUpComingAdapter"})
    public static void addMovieUpcomingAdapter(RecyclerView recyclerView,
                                               ArrayList<Movie> movies) {
        UpComingMovieAdapter adapter = (UpComingMovieAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(movies);
        }
    }

}
