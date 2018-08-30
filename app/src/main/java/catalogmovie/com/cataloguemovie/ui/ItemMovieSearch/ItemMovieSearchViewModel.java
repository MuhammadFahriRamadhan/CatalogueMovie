package catalogmovie.com.cataloguemovie.ui.ItemMovieSearch;

import android.databinding.ObservableField;



import catalogmovie.com.cataloguemovie.BuildConfig;
import catalogmovie.com.cataloguemovie.data.model.Movie;



public class ItemMovieSearchViewModel {

    public ObservableField<String> imageUrl;
    public ObservableField<String> title;
    public ObservableField<String> overview;
    public ObservableField<String> releaseDate;

    private Movie mMovie;

    public ItemMovieViewModelListener mListener;

    public ItemMovieSearchViewModel(Movie movie, ItemMovieViewModelListener listener) {
        this.mMovie = movie;
        this.mListener = listener;

        imageUrl = new ObservableField<>(BuildConfig.BASE_URL_POSTER_PATH_SMALL + mMovie.getPosterPath());
        title = new ObservableField<>(mMovie.getTitle());
        overview = new ObservableField<>(mMovie.getOverview());
        releaseDate = new ObservableField<>(mMovie.getReleaseDate());
    }

    public void gotoDetailMovieActivity() {
        mListener.gotoDetailMovieActivity(mMovie);
    }

    public interface ItemMovieViewModelListener {
        void gotoDetailMovieActivity(Movie movie);
    }
}
