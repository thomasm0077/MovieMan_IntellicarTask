package intellicar.movieman.retrofit

import intellicar.movieman.models.MovieDetailsModel
import intellicar.movieman.models.SearchModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //***********************************************************

    @GET(".")
    suspend fun getSearchData(

        @Query("s") searchString: String,
        @Query("apikey") apiKey: String,
        @Query("page") page: String

    ): SearchModel

    //***********************************************************

    @GET(".")
    suspend fun getMovieDetails(

        @Query("i") searchString: String,
        @Query("apikey") apiKey: String

    ): MovieDetailsModel

}