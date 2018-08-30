/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package catalogmovie.com.cataloguemovie.data;

import android.content.Context;


import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import catalogmovie.com.cataloguemovie.data.model.MovieResponse;
import catalogmovie.com.cataloguemovie.data.remote.ApiHelper;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mAPiHelper;

    @Inject
    public AppDataManager(Context mContext, ApiHelper apiHelper) {
        this.mContext = mContext;
        this.mAPiHelper = apiHelper;
    }



    @Override
    public Single<MovieResponse> getMovieSearchApiCall(String query) {
        return mAPiHelper.getMovieSearchApiCall(query);
    }


}
