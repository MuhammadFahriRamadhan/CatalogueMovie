package catalogmovie.com.cataloguemovie.ui.NowPlaying;

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
public class NowPlayingMovieFragmentModule {
    @Provides
    NowPlayingMovieViewModel provideNowPlayingViewModel(DataManager dataManager,
                                                   SchedulerProvider schedulerProvider) {
        return new NowPlayingMovieViewModel(dataManager, schedulerProvider);
    }

    @Provides
    NowPlayingMovieAdapter provideNowPlayingAdapter(DataManager dataManager,
                                               SchedulerProvider schedulerProvider) {
        return new NowPlayingMovieAdapter(new ArrayList<Movie>(), dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideNowPlayingLinearLayoutManager(NowPlayingMovieFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideNowPlayingViewModelProviderFactory(NowPlayingMovieViewModel blogViewModel) {
        return new ViewModelProviderFactory<>(blogViewModel);
    }
}
