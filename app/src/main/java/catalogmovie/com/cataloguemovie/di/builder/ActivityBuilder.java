package catalogmovie.com.cataloguemovie.di.builder;


import catalogmovie.com.cataloguemovie.ui.Home.HomeActivity;
import catalogmovie.com.cataloguemovie.ui.Home.HomeModule;
import catalogmovie.com.cataloguemovie.ui.Main.MainActivity;
import catalogmovie.com.cataloguemovie.ui.Main.MainModule;

import catalogmovie.com.cataloguemovie.ui.NowPlaying.NowPlayingMovieFragmentProvider;
import catalogmovie.com.cataloguemovie.ui.UpComing.UpComingMovieFragmentProvider;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivity;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {





    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = DetailMovieActivityModule.class)
    abstract DetailMovieActivity bindDetailMovieActivity();

    @ContributesAndroidInjector(modules = {
            HomeModule.class,
            NowPlayingMovieFragmentProvider.class,
            UpComingMovieFragmentProvider.class
    })
    abstract HomeActivity bindHomeActivity();


}
