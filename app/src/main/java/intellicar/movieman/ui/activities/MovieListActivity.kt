package intellicar.movieman.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import intellicar.movieman.R
import intellicar.movieman.adapters.MovieListAdapter
import intellicar.movieman.constants.MyConstants
import intellicar.movieman.models.SearchDataModel
import intellicar.movieman.utils.PaginationScrollListener
import intellicar.movieman.view_model.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*


class MovieListActivity : AppCompatActivity() {

    lateinit var viewModel: MovieListViewModel
    var dataList: ArrayList<SearchDataModel> = ArrayList()
    lateinit var recycler: RecyclerView
    lateinit var recyclerViewAdapter: MovieListAdapter

    lateinit var searchView: EditText
    var query = "har"

    var isLoading = false
    var isLastPage: Boolean = false
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        recycler = findViewById(R.id.movieList_recycler)

        searchView = findViewById(R.id.movieList_searchView) as EditText

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        recycler.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerViewAdapter = MovieListAdapter(this@MovieListActivity, dataList)
        recycler.adapter = recyclerViewAdapter

        searchView.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(querys: CharSequence?, p1: Int, p2: Int, p3: Int) {

                query = querys!!.toString()
                if (query.length >= 3) {
                    page = 1
                    isLoading = false
                    isLastPage = false
                    dataList.clear()
                    getData(query)
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


        searchView.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(tv: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    getData(tv?.text.toString())
                    return true;
                }

                return false;

            }

        })

        getData(query)


        recycler.addOnScrollListener(object :
            PaginationScrollListener(recycler.layoutManager as GridLayoutManager) {

            override fun isLastPage(): Boolean {

                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
//                isLoading = true
                if (!isLoading) {
                    page++
                    getData(query)
                }
            }

        })

    }

    fun getData(query: String) {

        isLoading = true
        movieList_progressBar.visibility = View.VISIBLE
        viewModel.getSearchResult(query, MyConstants.apiKey, page.toString())?.observe(
            this,
            Observer { response ->

                var totalPages = (response.totalResults) / 10

                if (response.totalResults != 0) {

                    movieList_noDataLayout.visibility = View.GONE
                    movieList_recycler.visibility = View.VISIBLE

                    dataList.addAll(response.Search)
                    isLoading = false
                    movieList_progressBar.visibility = View.GONE

                    recyclerViewAdapter.notifyDataSetChanged()

                    println("ResponseData::: ${dataList[0].Title}")

                    if (page > totalPages) {
                        isLastPage = true
                        movieList_progressBar.visibility = View.GONE
                    }

                } else {

                    isLoading = false
                    movieList_progressBar.visibility = View.GONE
                    movieList_recycler.visibility = View.GONE
                    movieList_noDataLayout.visibility = View.VISIBLE

                }

            })

    }

}