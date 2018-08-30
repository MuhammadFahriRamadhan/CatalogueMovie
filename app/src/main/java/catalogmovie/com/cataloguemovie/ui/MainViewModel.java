package catalogmovie.com.cataloguemovie.ui;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";
    private final ObservableArrayList<Movie> searchMovieObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<Movie>> searchMovieListLiveData;


    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        searchMovieListLiveData = new MutableLiveData<>();
    }


    public void searchClick(){
        getNavigator().search();
    }

    public void doSearchMovieList(String query) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getMovieSearchApiCall(query)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Exception {

                        setIsLoading(false);
                        searchMovieListLiveData.setValue(movieResponse.getResults());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: ", throwable);

                        setIsLoading(false);
                        getNavigator().failedLoadApi();
                    }
                }));
    }

    public MutableLiveData<List<Movie>> getSearchMovieListLiveData() {
        return searchMovieListLiveData;
    }

    public ObservableArrayList<Movie> getMovieObservableArrayList() {
        return searchMovieObservableArrayList;
    }

    public void addSearchMovieItemsToList(List<Movie> movies) {
        searchMovieObservableArrayList.clear();
        searchMovieObservableArrayList.addAll(movies);
    }
}