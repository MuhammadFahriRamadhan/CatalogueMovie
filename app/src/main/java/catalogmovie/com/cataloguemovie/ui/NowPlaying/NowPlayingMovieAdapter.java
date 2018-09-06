package catalogmovie.com.cataloguemovie.ui.NowPlaying;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.ItemNowplayingMovieBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewHolder;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivity;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

public class NowPlayingMovieAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Movie> mMovieList;

    private DataManager mDataManager;
    private SchedulerProvider mSchedulerProvider;

    private NowPlayingAdapterListener mListener;

    public NowPlayingMovieAdapter(List<Movie> movies, DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.mMovieList = movies;
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    public void setListener(NowPlayingAdapterListener listener) {
        this.mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemNowplayingMovieBinding binding = ItemNowplayingMovieBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new NowPlayingMovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void addItems(List<Movie> movies) {
        mMovieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mMovieList.clear();
    }

    public class NowPlayingMovieViewHolder extends BaseViewHolder implements
            ItemNowPlayingMovieViewModel.MovieNowPlayingItemViewModelListener {

        private ItemNowplayingMovieBinding mBinding;

        private ItemNowPlayingMovieViewModel mViewModel;

        public NowPlayingMovieViewHolder(ItemNowplayingMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Movie movie = mMovieList.get(position);

            mViewModel = new ItemNowPlayingMovieViewModel(mDataManager, mSchedulerProvider);
            mViewModel.setMovie(movie, this);
            mBinding.setViewModel(mViewModel);

            mBinding.executePendingBindings();
        }

        @Override
        public void gotoDetailMovieActivity(Movie movie) {
            Context context = mBinding.getRoot().getContext();
            context.startActivity(DetailMovieActivity.gotoDetailMovieActivity(context, movie));
        }
    }

    public interface NowPlayingAdapterListener {
        void onRetryClick();
    }
}
