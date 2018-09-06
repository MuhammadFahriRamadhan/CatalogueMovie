package catalogmovie.com.cataloguemovie.ui.UpComing;

import android.databinding.ObservableField;

import catalogmovie.com.cataloguemovie.BuildConfig;
import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

public class ItemUpComingMovieViewModel  extends BaseViewModel {

    private Movie mMovie;

    public ObservableField<String> imageUrl;
    public ObservableField<String> title;
    public ObservableField<String> overview;
    public ObservableField<String> releaseDate;

    public MovieUpComingItemViewModelListener mListener;

    public ItemUpComingMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setMovie(Movie movie, MovieUpComingItemViewModelListener listener) {
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

    public void shareMovie() {
        getDataManager().shareToSocialMedia(mMovie.getPosterPath());
    }

    public interface MovieUpComingItemViewModelListener {
        void gotoDetailMovieActivity(Movie movie);
    }
}