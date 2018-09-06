package catalogmovie.com.cataloguemovie.data.remote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;

import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import io.reactivex.Single;


public class AppApiHelper implements ApiHelper {

    private final Context mContext;

    @Inject
    public AppApiHelper(Context context) {
        this.mContext = context;
    }


    @Override
    public Single<MovieResponse> getMovieSearchApiCall(String query) {
        return Rx2AndroidNetworking.get(ApiEndPoint.END_POINT_MOVIE_API+"search/movie?")
                .addQueryParameter("api_key", ApiEndPoint.API_KEY)
                .addQueryParameter("language", "en-US")
                .addQueryParameter("query", query)
                .build()
                .getObjectSingle(MovieResponse.class);
    }

    @Override
    public Single<MovieResponse> getMovieUpcomingApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.END_POINT_MOVIE_UPCOMING_API)
                .addQueryParameter("api_key", ApiEndPoint.API_KEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getObjectSingle(MovieResponse.class);
    }


    @Override
    public Single<MovieResponse> getMovieNowPlayingApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.END_POINT_MOVIE_NOW_PLAYING_API)
                .addQueryParameter("api_key", ApiEndPoint.API_KEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getObjectSingle(MovieResponse.class);
    }


    @Override
    public void shareToSocialMedia(String imageUrl) {
        String text = mContext.getResources().getString(R.string.share);
        Uri pictureUri = Uri.parse(imageUrl);
        Intent shareIntent = new Intent();
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, pictureUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        mContext.startActivity(Intent.createChooser(shareIntent,
                mContext.getResources().getString(R.string.share_image_url))
        );
    }
}
