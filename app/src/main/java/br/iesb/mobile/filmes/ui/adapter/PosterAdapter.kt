package br.iesb.mobile.filmes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.filmes.databinding.ItemPosterBinding
import br.iesb.mobile.filmes.domain.filmes.Poster
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PosterAdapter @Inject constructor() :
    PagingDataAdapter<Poster, PosterAdapter.PosterViewHolder>(PosterComparator){
    var characterClickListener: CharacterClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PosterViewHolder(
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PosterViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                characterClickListener?.onCharacterClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as Poster
                )
            }
        }

        fun bind(item: Poster) = with(binding) {

            Picasso.get().load(StringBuffer("https://image.tmdb.org/t/p/original/").append(item.poster_path)
                .toString()).into(binding.ivAvatar)

           // ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${item.id}")
            ViewCompat.setTransitionName(binding.tvName, "name_${item.id}")
            ViewCompat.setTransitionName(binding.tvStatus, "status_${item.id}")
           // ViewCompat.setTransitionName(binding.tvDate, item.release_date)
            poster = item
        }
    }

    object PosterComparator : DiffUtil.ItemCallback<Poster>() {
        override fun areItemsTheSame(oldItem: Poster, newItem: Poster) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Poster, newItem: Poster) =
            oldItem == newItem
    }

    interface CharacterClickListener {
        fun onCharacterClicked(binding: ItemPosterBinding, poster: Poster)
    }

}