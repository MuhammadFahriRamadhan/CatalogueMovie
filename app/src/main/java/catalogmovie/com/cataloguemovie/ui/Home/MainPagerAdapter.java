package catalogmovie.com.cataloguemovie.ui.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import catalogmovie.com.cataloguemovie.ui.NowPlaying.NowPlayingMovieFragment;
import catalogmovie.com.cataloguemovie.ui.UpComing.UpComingMovieFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MainPagerAdapter";

    private int mTabCount;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
             return NowPlayingMovieFragment.newInstance();

            case 1:
                return UpComingMovieFragment.newInstance();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
