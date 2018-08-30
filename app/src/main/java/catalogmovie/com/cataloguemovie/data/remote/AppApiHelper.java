package catalogmovie.com.cataloguemovie.data.remote;

import android.content.Context;


import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;

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
}
