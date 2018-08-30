package catalogmovie.com.cataloguemovie.ui.detail;

import android.databinding.ObservableField;

import catalogmovie.com.cataloguemovie.BuildConfig;
import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

import static catalogmovie.com.cataloguemovie.utils.CommonUtils.converDate;


public class DetailMovieViewModel extends BaseViewModel<DetailMovieNavigator> {

    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> overview = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    public DetailMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setDataMovie(Movie result) {
        this.img.set(BuildConfig.POSTER+result.getPosterPath());
        this.date.set("Release Date : "+converDate(result.getReleaseDate()));
        this.title.set(result.getTitle());
        this.overview.set("Description : "+result.getOverview());
    }
}
