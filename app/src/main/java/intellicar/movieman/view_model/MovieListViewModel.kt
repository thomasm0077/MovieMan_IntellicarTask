package intellicar.movieman.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import intellicar.movieman.models.SearchModel
import intellicar.movieman.repository.MovieListRepository

class MovieListViewModel: ViewModel() {

    var data: MutableLiveData<SearchModel>? = null
    var progressStatus: Boolean? = null

    fun getSearchResult(query: String, apiKey: String, page: String): MutableLiveData<SearchModel>?{

        data = MovieListRepository.getMovieList(query, apiKey, page.toString())
        return data
    }


    fun cancelJobs(){
        MovieListRepository.cancelJobs()
    }

}