package intellicar.movieman.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import intellicar.movieman.models.MovieDetailsModel
import intellicar.movieman.repository.MovieDetailsRepository
import intellicar.movieman.repository.MovieListRepository

class MovieDetailsViewModel: ViewModel() {

    var data: MutableLiveData<MovieDetailsModel>? = null

    fun getMovieResult(imdbId: String, apiKey: String): MutableLiveData<MovieDetailsModel>?{

        data = MovieDetailsRepository.getMovieDetails(imdbId, apiKey)
        return data
    }


    fun cancelJobs(){
        MovieListRepository.cancelJobs()
    }

}