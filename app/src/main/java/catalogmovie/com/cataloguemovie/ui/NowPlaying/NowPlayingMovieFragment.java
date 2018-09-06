package catalogmovie.com.cataloguemovie.ui.NowPlaying;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.BR;
import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.FragmentNowplayingMovieBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseFragment;

public class NowPlayingMovieFragment extends BaseFragment<FragmentNowplayingMovieBinding, NowPlayingMovieViewModel>
        implements NowPlayingMovieFragmentNavigator, NowPlayingMovieAdapter.NowPlayingAdapterListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    NowPlayingMovieAdapter mNowPlayingMovieAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private NowPlayingMovieViewModel mNowPlayingMovieViewModel;

    FragmentNowplayingMovieBinding mFragmentNowPlayingMovieBinding;

    public static NowPlayingMovieFragment newInstance() {
        Bundle args = new Bundle();
        NowPlayingMovieFragment fragment = new NowPlayingMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNowPlayingMovieViewModel.setNavigator(this);
        mNowPlayingMovieAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentNowPlayingMovieBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
//        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentNowPlayingMovieBinding.nowPlayingRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentNowPlayingMovieBinding.nowPlayingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentNowPlayingMovieBinding.nowPlayingRecyclerView.setAdapter(mNowPlayingMovieAdapter);
    }

    private void subscribeToLiveData() {
        mNowPlayingMovieViewModel.getNowPlayingMovieListLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mNowPlayingMovieViewModel.addNowPlayingMovieItemsToList(movies);
            }
        });
    }

    @Override
    public NowPlayingMovieViewModel getViewModel() {
        mNowPlayingMovieViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NowPlayingMovieViewModel.class);
        return mNowPlayingMovieViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nowplaying_movie;
    }

    @Override
    public void onRetryClick() {

    }
}

