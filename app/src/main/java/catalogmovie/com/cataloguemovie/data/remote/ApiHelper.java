package catalogmovie.com.cataloguemovie.data.remote;



import java.util.List;

import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import io.reactivex.Single;


public interface ApiHelper {



    Single<MovieResponse> getMovieSearchApiCall(String query);

}
