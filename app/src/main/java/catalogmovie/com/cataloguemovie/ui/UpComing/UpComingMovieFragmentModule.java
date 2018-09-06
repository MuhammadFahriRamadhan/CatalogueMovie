package catalogmovie.com.cataloguemovie.ui.UpComing;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import catalogmovie.com.cataloguemovie.ViewModelProviderFactory;
import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class UpComingMovieFragmentModule {

    @Provides
    UpComingMovieViewModel provideNowPlayingViewModel(DataManager dataManager,
                                                        SchedulerProvider schedulerProvider) {
        return new UpComingMovieViewModel(dataManager, schedulerProvider);
    }

    @Provides
    UpComingMovieAdapter provideNowPlayingAdapter(DataManager dataManager,
                                                    SchedulerProvider schedulerProvider) {
        return new UpComingMovieAdapter(new ArrayList<Movie>(), dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideNowPlayingLinearLayoutManager(UpComingMovieFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideNowPlayingViewModelProviderFactory(UpComingMovieViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }
}
