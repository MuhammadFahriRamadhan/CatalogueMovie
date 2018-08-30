package catalogmovie.com.cataloguemovie.di.builder;


import catalogmovie.com.cataloguemovie.ui.Main.MainActivity;
import catalogmovie.com.cataloguemovie.ui.Main.MainModule;

import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivity;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {



//    @ContributesAndroidInjector(modules = SearchActivityModule.class)
//    abstract SearchActivity bindSearchActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = DetailMovieActivityModule.class)
    abstract DetailMovieActivity bindDetailMovieActivity();


}
