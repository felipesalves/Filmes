package br.iesb.mobile.filmes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.databinding.FragmentItemGenresFilmeBinding
import br.iesb.mobile.filmes.domain.filmes.Genres


class GenresAdapter(
        private val genres: List<Genres>,
        private val genresItem: ((Genres) -> Unit)
) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_item_genres_filme,
                parent,
                false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.genres = genres[position]
        binding?.executePendingBindings()

    }


    override fun getItemCount() = genres.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding: FragmentItemGenresFilmeBinding? = FragmentItemGenresFilmeBinding.bind(view)

    }

}