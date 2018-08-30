package catalogmovie.com.cataloguemovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.BR;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.ActivityDetailMovieBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseActivity;

public class DetailMovieActivity extends BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel> implements DetailMovieNavigator {

    @Inject
    DetailMovieViewModel mDetailMovieViewModel;

    private ActivityDetailMovieBinding mActivityDetailMovieBinding;

    public static Intent gotoDetailMovieActivity(Context context, Movie movie){
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra("dataMovie", movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDetailMovieBinding = getViewDataBinding();
        getDataIntent();
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        Movie result = intent.getParcelableExtra("dataMovie");
        mDetailMovieViewModel.setDataMovie(result);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_movie;
    }

    @Override
    public DetailMovieViewModel getViewModel() {
        return mDetailMovieViewModel;
    }
}
