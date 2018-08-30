package catalogmovie.com.cataloguemovie.ui.Main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.BR;
import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.ActivityMainBinding;
import catalogmovie.com.cataloguemovie.ui.ItemMovieSearch.MovieSearchAdapter;
import catalogmovie.com.cataloguemovie.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> implements MainNavigator {
    private static final String TAG = "MainActivity";

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    MainViewModel mMainViewModel;
    private ActivityMainBinding mActivitymainbinding;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    MovieSearchAdapter mMoiewsearch;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    public static Intent gotoSearchActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitymainbinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
            setUp();
            subscribeToLiveData();
    }



    @Override
    public void search() {
        String inputSearh = mActivitymainbinding.edtcari.getText().toString();

        if (!inputSearh.equals("") && isNetworkConnected()){

            mMainViewModel.doSearchMovieList(inputSearh);
        }else if (inputSearh.equals("")){
            Toast.makeText(this, "Inputan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Harap Cek Koneksi Anda", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void nullData() {
        Toast.makeText(this, "Kata Kunci Tidak Ditemukan", Toast.LENGTH_SHORT).show();
    }
    private void setUp() {
        mActivitymainbinding.listmovie.setHasFixedSize(true);
        mActivitymainbinding.listmovie.setLayoutManager(mLinearLayoutManager);
        //mActivitySearchBinding.movieListRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mActivitymainbinding.listmovie.setAdapter(mMoiewsearch);
    }

    private void subscribeToLiveData() {
        mMainViewModel.getSearchMovieListLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mMainViewModel.addSearchMovieItemsToList(movies);
            }
        });
    }

    @Override
    public void failedLoadApi() {

    }

    @Override
    public void actionSearch() {

    }

}
