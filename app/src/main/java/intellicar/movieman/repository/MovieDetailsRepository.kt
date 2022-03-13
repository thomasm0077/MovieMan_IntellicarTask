package intellicar.movieman.repository

import androidx.lifecycle.MutableLiveData
import intellicar.movieman.models.MovieDetailsModel
import intellicar.movieman.retrofit.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MovieDetailsRepository {

    var job: CompletableJob? = null

    fun getMovieDetails(imdbId: String, apiKey: String): MutableLiveData<MovieDetailsModel>{

        job = Job()

        return object : MutableLiveData<MovieDetailsModel>() {

            override fun onActive() {
                super.onActive()
                job?.let { myJob ->

                    CoroutineScope(IO + myJob).launch {

                        val data = RetrofitClient.apiService.getMovieDetails(imdbId, apiKey)

                        withContext(Main){

                            value = data
                            job?.complete()

                        }

                    }

                }
            }
        }

    }

    fun cancelJobs(){
        job?.cancel()
    }

}