package intellicar.movieman.repository

import androidx.lifecycle.MutableLiveData
import intellicar.movieman.models.SearchModel
import intellicar.movieman.retrofit.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MovieListRepository {

    var job: CompletableJob? = null
    var progress: Boolean = false

    fun getMovieList(query: String, apiKey: String, page: String): MutableLiveData<SearchModel>{

        job = Job()
        progress = true

        return object : MutableLiveData<SearchModel>() {

            override fun onActive() {
                super.onActive()
                job?.let { myJob ->

                    CoroutineScope(IO + myJob).launch {

                        val data = RetrofitClient.apiService.getSearchData(query, apiKey, page)

                        withContext(Main){

                            value = data
                            job?.complete()
                            progress = false

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