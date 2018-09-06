package catalogmovie.com.cataloguemovie.di.component;

import android.app.Application;


import javax.inject.Singleton;

import catalogmovie.com.cataloguemovie.CatalogueMovieApp;
import catalogmovie.com.cataloguemovie.di.builder.ActivityBuilder;
import catalogmovie.com.cataloguemovie.di.module.AppModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(CatalogueMovieApp app);
}
