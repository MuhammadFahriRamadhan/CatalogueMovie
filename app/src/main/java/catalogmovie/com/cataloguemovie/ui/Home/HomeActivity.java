package catalogmovie.com.cataloguemovie.ui.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.BR;
import catalogmovie.com.cataloguemovie.databinding.ActivityHomeBinding;
import catalogmovie.com.cataloguemovie.ui.Main.MainActivity;
import catalogmovie.com.cataloguemovie.ui.Main.MainViewModel;
import catalogmovie.com.cataloguemovie.ui.base.BaseActivity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity  extends BaseActivity<ActivityHomeBinding, HomeViewModel>
        implements HasSupportFragmentInjector, HomeNavigator{

    private static final String TAG = "MainActivity";

    @Inject
    MainPagerAdapter mPagerAdapter;

    @Inject
    HomeViewModel mHomeViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;



    ActivityHomeBinding mActivityHomeBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding = getViewDataBinding();
        mHomeViewModel.setNavigator(this);
        setUp();

       setupTabIcons();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_settings:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                Log.d(TAG, "onOptionsItemSelected: seacrh");
                return true;

            case R.id.action_search:
                startActivity(MainActivity.gotoSearchActivity(this));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void setupTabIcons() {
        mActivityHomeBinding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_play_circle_filled_black_24dp);
        mActivityHomeBinding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_movies_black_24dp);

    }

    private void setUp() {
        setSupportActionBar(mActivityHomeBinding.toolbar);

        mPagerAdapter.setCount(2);

        mActivityHomeBinding.baseViewPager.setAdapter(mPagerAdapter);

        mActivityHomeBinding.tabLayout.addTab(mActivityHomeBinding.tabLayout.newTab().setText(R.string.now_playing));
        mActivityHomeBinding.tabLayout.addTab(mActivityHomeBinding.tabLayout.newTab().setText(getString(R.string.upcoming)));
        mActivityHomeBinding.baseViewPager.setOffscreenPageLimit(mActivityHomeBinding.tabLayout.getTabCount());
        mActivityHomeBinding.baseViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mActivityHomeBinding.tabLayout));

        mActivityHomeBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mActivityHomeBinding.baseViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }




    @Override
    public HomeViewModel getViewModel() {
        return mHomeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }




    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }


}

