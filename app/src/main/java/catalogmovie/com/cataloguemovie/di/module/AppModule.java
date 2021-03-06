package catalogmovie.com.cataloguemovie.di.module;

import android.app.Application;

import android.arch.persistence.room.Room;
import android.content.Context;


import javax.inject.Singleton;

import catalogmovie.com.cataloguemovie.BuildConfig;
import catalogmovie.com.cataloguemovie.R;

import catalogmovie.com.cataloguemovie.data.AppDataManager;
import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.remote.ApiHelper;
import catalogmovie.com.cataloguemovie.data.remote.AppApiHelper;

import catalogmovie.com.cataloguemovie.utils.rx.AppSchedulerProvider;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
