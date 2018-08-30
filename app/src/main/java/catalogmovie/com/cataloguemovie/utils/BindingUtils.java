package catalogmovie.com.cataloguemovie.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.ui.ItemMovieSearch.MovieSearchAdapter;

/**
 * Created by irmansyah on 23/02/18.
 */

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
//
    @BindingAdapter({"movieSearchAdapter"})
    public static void addMovieSearchAdapter(RecyclerView recyclerView,
                                     ArrayList<Movie> movies) {
        MovieSearchAdapter adapter = (MovieSearchAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(movies);
        }
    }
//
//    @BindingAdapter({"movieNowPlayingAdapter"})
//    public static void addMovieNowPlayingAdapter(RecyclerView recyclerView,
//                                       ArrayList<Movie> movies) {
//        NowPlayingAdapter adapter = (NowPlayingAdapter) recyclerView.getAdapter();
//        if(adapter != null) {
//            adapter.clearItems();
//            adapter.addItems(movies);
//        }
//    }
//
//    @BindingAdapter({"movieUpcomingAdapter"})
//    public static void addMovieUpcomingAdapter(RecyclerView recyclerView,
//                                                 ArrayList<Movie> movies) {
//        UpcomingAdapter adapter = (UpcomingAdapter) recyclerView.getAdapter();
//        if(adapter != null) {
//            adapter.clearItems();
//            adapter.addItems(movies);
//        }
//    }
//
//    @BindingAdapter({"movieDbFAvouriteAdapter"})
//    public static void addMovieDbFAvouriteAdapter(RecyclerView recyclerView,
//                                               ArrayList<MovieDb> movieDbs) {
//        MovieDbFavouriteAdapter adapter = (MovieDbFavouriteAdapter) recyclerView.getAdapter();
//        if(adapter != null) {
//            adapter.clearItems();
//            adapter.addItems(movieDbs);
//        }
//    }
}
