package catalogmovie.com.cataloguemovie.ui.NowPlaying;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NowPlayingMovieFragmentProvider {

    @ContributesAndroidInjector(modules = NowPlayingMovieFragmentModule.class)
    abstract NowPlayingMovieFragment provideNowPlayingFragmentFactory();
}
