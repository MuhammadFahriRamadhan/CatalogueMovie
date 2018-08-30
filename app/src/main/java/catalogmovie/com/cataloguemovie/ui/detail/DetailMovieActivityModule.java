package catalogmovie.com.cataloguemovie.ui.detail;



import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class DetailMovieActivityModule {

    @Provides
    DetailMovieViewModel provideDetailMovieViewModel(DataManager dataManager,
                                                     SchedulerProvider schedulerProvider){
        return new DetailMovieViewModel(dataManager, schedulerProvider);
    }


}
