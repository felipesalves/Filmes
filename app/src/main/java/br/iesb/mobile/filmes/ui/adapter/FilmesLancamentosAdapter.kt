package br.iesb.mobile.filmes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.databinding.FragmentItemLancamentosFilmeBinding
import br.iesb.mobile.filmes.domain.filmes.FilmesLancamentos
import com.squareup.picasso.Picasso

class FilmesLancamentosAdapter(
        private val filmes: List<FilmesLancamentos>,
        private val filmeItem: ((FilmesLancamentos) -> Unit)
) : RecyclerView.Adapter<FilmesLancamentosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_item_lancamentos_filme,
                parent,
                false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.filmes = filmes[position]
        binding?.executePendingBindings()

        if (binding != null) {
          Picasso.get().load(StringBuffer("https://image.tmdb.org/t/p/original/").append(binding?.filmes?.poster_path)
                    .toString()).into(binding.poster)
        }
    }


    override fun getItemCount() = filmes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding: FragmentItemLancamentosFilmeBinding? = FragmentItemLancamentosFilmeBinding.bind(view)

        init {
            view.setOnClickListener{
                filmeItem.invoke(filmes[adapterPosition])
            }
        }
    }

}