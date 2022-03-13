package intellicar.movieman.retrofit

import intellicar.movieman.constants.MyConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(MyConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiInterface by lazy {

        retrofitBuilder
                .build()
                .create(ApiInterface::class.java)
    }
}