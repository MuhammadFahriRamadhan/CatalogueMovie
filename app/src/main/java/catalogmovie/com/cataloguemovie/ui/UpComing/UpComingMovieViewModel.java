package catalogmovie.com.cataloguemovie.ui.UpComing;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.List;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

public class UpComingMovieViewModel extends BaseViewModel<UpComingMovieFragmentNavigator> {

    private static final String TAG = "NowPlayingViewModel";

    private final ObservableArrayList<Movie> nowPlayingMovieObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<Movie>> nowPlayingMovieListLiveData;

    public UpComingMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        nowPlayingMovieListLiveData = new MutableLiveData<>();

        doRequestMovieUpComing();
    }

    private void doRequestMovieUpComing() {
        getCompositeDisposable().add(getDataManager().getMovieUpcomingApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {
                        for (int i = 0; i < movieResponse.getResults().size(); i++) {
                            Log.i(TAG, "accept: " + movieResponse.getResults().get(i).getTitle());
                        }
                        nowPlayingMovieListLiveData.setValue(movieResponse.getResults());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: ", throwable);
                    }
                }));
    }

    public MutableLiveData<List<Movie>> getUpComingMovieListLiveData() {
        return nowPlayingMovieListLiveData;
    }

    public ObservableArrayList<Movie> getUpComingMovieObservableArrayList() {
        return nowPlayingMovieObservableArrayList;
    }

    public void addUpComingMovieItemsToList(List<Movie> movies) {
        nowPlayingMovieObservableArrayList.clear();
        nowPlayingMovieObservableArrayList.addAll(movies);
    }
}
