package intellicar.movieman.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import intellicar.movieman.R
import kotlinx.android.synthetic.main.movie_genre_child.view.*

class MovieGenreAdapter(val context: Context, var data: ArrayList<String>) : RecyclerView.Adapter<MovieGenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGenreAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_genre_child,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return data.size

    }

    override fun onBindViewHolder(holder: MovieGenreAdapter.ViewHolder, position: Int) {

        holder.bindItems(context, position, data[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(context: Context, position: Int, data: String) {

            itemView.movieGenreChild_genre.text = data

        }

    }


}