package catalogmovie.com.cataloguemovie.ui.UpComing;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class UpComingMovieFragmentProvider {
    @ContributesAndroidInjector(modules = UpComingMovieFragmentModule.class)
    abstract UpComingMovieFragment provideNowPlayingFragmentFactory();
}
