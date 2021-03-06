package catalogmovie.com.cataloguemovie.ui.NowPlaying;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.List;
import io.reactivex.functions.Consumer;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

public class NowPlayingMovieViewModel extends BaseViewModel<NowPlayingMovieFragmentNavigator> {

    private static final String TAG = "NowPlayingViewModel";

    private final ObservableArrayList<Movie> nowPlayingMovieObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<Movie>> nowPlayingMovieListLiveData;

    public NowPlayingMovieViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        nowPlayingMovieListLiveData = new MutableLiveData<>();

        doRequestMovieNowPlaying();
    }

    private void doRequestMovieNowPlaying() {
        getCompositeDisposable().add(getDataManager().getMovieNowPlayingApiCall()
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

    public MutableLiveData<List<Movie>> getNowPlayingMovieListLiveData() {
        return nowPlayingMovieListLiveData;
    }

    public ObservableArrayList<Movie> getNowPlayingMovieObservableArrayList() {
        return nowPlayingMovieObservableArrayList;
    }

    public void addNowPlayingMovieItemsToList(List<Movie> movies) {
        nowPlayingMovieObservableArrayList.clear();
        nowPlayingMovieObservableArrayList.addAll(movies);
    }
}
