package catalogmovie.com.cataloguemovie.ui.Home;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewModel;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

public class HomeViewModel  extends BaseViewModel<HomeNavigator> {

    private static final String TAG = "MainViewModel";

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }



}
