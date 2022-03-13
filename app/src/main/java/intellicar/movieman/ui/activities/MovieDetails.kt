package intellicar.movieman.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import intellicar.movieman.R
import intellicar.movieman.adapters.MovieGenreAdapter
import intellicar.movieman.constants.MyConstants
import intellicar.movieman.view_model.MovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetails : AppCompatActivity() {

    lateinit var viewModel: MovieDetailsViewModel

    lateinit var recycler: RecyclerView
    lateinit var recyclerViewAdapter: MovieGenreAdapter
    var genreArray: List<String> = ArrayList()
    var imdbId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        imdbId = intent.getStringExtra("imdbId").toString()

        recycler = findViewById(R.id.movieDetails_genreRecycler)
        recycler.layoutManager = GridLayoutManager(applicationContext, 2)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.getMovieResult(imdbId, MyConstants.apiKey)?.observe(this, Observer { data ->

            println("ResponseTitle::: ${data.Title}")

            Glide.with(this).load(data.Poster).placeholder(R.drawable.ic_no_poster).into(
                movieDetails_poster
            )
            movieDetails_title.text = data.Title
            movieDetails_rating.text = data.imdbRating
            movieDetails_time.text = data.Runtime
            movieDetails_plot.text = data.Plot
            movieDetails_director.text = "Director: ${data.Director}"
            movieDetails_writer.text = "Writer: ${data.Writer}"
            movieDetails_actor.text = "Actors: ${data.Actors}"
            movieDetails_year.text = "Year: ${data.Year}"
            var genre = data.Genre
            genreArray = genre.split(",").map { it -> it.trim() }
            recyclerViewAdapter = MovieGenreAdapter(this, genreArray as ArrayList<String>)
            recycler.adapter = recyclerViewAdapter
        })

        movieDetails_back.setOnClickListener {

            onBackPressed()

        }
    }

}