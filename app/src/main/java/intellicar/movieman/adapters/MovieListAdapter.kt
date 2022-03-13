package intellicar.movieman.adapters

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import intellicar.movieman.R
import intellicar.movieman.models.SearchDataModel
import intellicar.movieman.ui.activities.MovieDetails
import kotlinx.android.synthetic.main.movie_list_child.view.*

class MovieListAdapter(val context: Context, var data: ArrayList<SearchDataModel>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_child,
            parent,
            false
        )
        val height = parent.measuredHeight / 4
        v.setMinimumHeight(height)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return data.size

    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {

        holder.bindItems(context, position, data[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(context: Context, position: Int, data: SearchDataModel) {

            itemView.movieListChild_name.text = data.Title
            itemView.movieListChild_year.text = data.Year

            Glide.with(context).load(data.Poster).placeholder(R.drawable.ic_no_poster).listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        itemView.movieListChild_progress.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        itemView.movieListChild_progress.visibility = View.GONE
                        return false
                    }

                }).into(itemView.movieListChild_imageView)

            itemView.movieListChild_main.setOnClickListener {

                var i = Intent(context, MovieDetails::class.java)
                i.putExtra("imdbId", data.imdbID)
                context.startActivity(i)

            }

        }

    }


}