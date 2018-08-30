package catalogmovie.com.cataloguemovie.utils;

import java.util.ArrayList;
import java.util.List;

import catalogmovie.com.cataloguemovie.R;
import catalogmovie.com.cataloguemovie.data.model.Movie;

public class DataList {

    public static List<Movie> getDataList() {
        List<Movie> list = new ArrayList<>();

            list.add(new Movie("https://www.isme.ie/assets/Retail-Sales-Press-Release-300x225.jpg", "","",""));

        return list;
    }
}
