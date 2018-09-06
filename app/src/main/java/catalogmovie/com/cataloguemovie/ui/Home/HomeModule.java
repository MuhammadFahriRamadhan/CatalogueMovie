package catalogmovie.com.cataloguemovie.ui.Home;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider) {
        return new HomeViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MainPagerAdapter provideMainPagerAdapter(HomeActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }
}
