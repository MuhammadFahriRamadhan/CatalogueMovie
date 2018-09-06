package catalogmovie.com.cataloguemovie.ui.UpComing;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.arch.lifecycle.Observer;
import java.util.List;

import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.BR;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.FragmentUpcomingMovieBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseFragment;

public class UpComingMovieFragment extends BaseFragment<FragmentUpcomingMovieBinding, UpComingMovieViewModel>
        implements UpComingMovieFragmentNavigator , UpComingMovieAdapter.NowPlayingAdapterListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UpComingMovieAdapter mUpComingMovieAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private UpComingMovieViewModel mUpComingMovieViewModel;

    FragmentUpcomingMovieBinding mFragmentUpComingMovieBinding;

    public static UpComingMovieFragment newInstance() {
        Bundle args = new Bundle();
        UpComingMovieFragment fragment = new UpComingMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpComingMovieViewModel.setNavigator(this);
        mUpComingMovieAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentUpComingMovieBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentUpComingMovieBinding.UpComingRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentUpComingMovieBinding.UpComingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentUpComingMovieBinding.UpComingRecyclerView.setAdapter(mUpComingMovieAdapter);
    }

    private void subscribeToLiveData() {
        mUpComingMovieViewModel.getUpComingMovieListLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                mUpComingMovieViewModel.addUpComingMovieItemsToList(movies);
            }
        });
    }

    @Override
    public UpComingMovieViewModel getViewModel() {
        mUpComingMovieViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UpComingMovieViewModel.class);
        return mUpComingMovieViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_upcoming_movie;
    }

    @Override
    public void onRetryClick() {

    }
}

