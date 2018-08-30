package catalogmovie.com.cataloguemovie.ui.Main;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import catalogmovie.com.cataloguemovie.ViewModelProviderFactory;
import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.ui.ItemMovieSearch.MovieSearchAdapter;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }


    @Provides
    ViewModelProvider.Factory provideMainViewModelProviderFactory(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MovieSearchAdapter provideMainAdapter(){
        return new MovieSearchAdapter(new ArrayList<Movie>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MainActivity activity){
        return new LinearLayoutManager(activity.getApplication());
    }

}
