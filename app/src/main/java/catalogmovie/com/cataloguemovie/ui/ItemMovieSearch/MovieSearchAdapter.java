package catalogmovie.com.cataloguemovie.ui.ItemMovieSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

import catalogmovie.com.cataloguemovie.data.model.Movie;
import catalogmovie.com.cataloguemovie.databinding.ItemMovieSearchBinding;
import catalogmovie.com.cataloguemovie.ui.base.BaseViewHolder;
import catalogmovie.com.cataloguemovie.ui.detail.DetailMovieActivity;


public class MovieSearchAdapter extends RecyclerView.Adapter<BaseViewHolder>{


    private List<Movie> movieList;

    public MovieSearchAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    public void addItems(List<Movie> movies) {
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void clearItems() {
        movieList.clear();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieSearchBinding binding = ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends BaseViewHolder implements
            ItemMovieSearchViewModel.ItemMovieViewModelListener {

        private ItemMovieSearchBinding mBinding;
        private ItemMovieSearchViewModel mMovieItemViewModel;

        public MovieViewHolder(ItemMovieSearchBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Movie movie = movieList.get(position);
            mMovieItemViewModel = new ItemMovieSearchViewModel(movie, this);
            mBinding.setViewModel(mMovieItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void gotoDetailMovieActivity(Movie movie) {
            Context context = mBinding.getRoot().getContext();
            context.startActivity(DetailMovieActivity.gotoDetailMovieActivity(context, movie));

        }
    }
}
