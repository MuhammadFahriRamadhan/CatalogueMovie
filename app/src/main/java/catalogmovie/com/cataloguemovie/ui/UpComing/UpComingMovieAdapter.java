package catalogmovie.com.cataloguemovie.ui.UpComing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import catalogmovie.com.cataloguemovie.data.DataManager;
import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.ItemUpcomingMovieBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewHolder;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivity;
import catalogmovie.com.cataloguemovie.utils.rx.SchedulerProvider;

public class UpComingMovieAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Movie> mMovieList;

    private DataManager mDataManager;
    private SchedulerProvider mSchedulerProvider;

    private NowPlayingAdapterListener mListener;

    public UpComingMovieAdapter(List<Movie> movies, DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.mMovieList = movies;
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    public void setListener(NowPlayingAdapterListener listener) {
        this.mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUpcomingMovieBinding binding = ItemUpcomingMovieBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new UpComingMovieViewHolder(binding);
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

    public class UpComingMovieViewHolder extends BaseViewHolder implements
            ItemUpComingMovieViewModel.MovieUpComingItemViewModelListener {

        private ItemUpcomingMovieBinding mBinding;

        private ItemUpComingMovieViewModel mViewModel;

        public UpComingMovieViewHolder(ItemUpcomingMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Movie movie = mMovieList.get(position);

            mViewModel = new ItemUpComingMovieViewModel(mDataManager, mSchedulerProvider);
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
