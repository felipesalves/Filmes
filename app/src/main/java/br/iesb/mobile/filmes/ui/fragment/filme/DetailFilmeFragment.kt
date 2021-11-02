package br.iesb.mobile.filmes.ui.fragment.filme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.databinding.FragmentDetailFilmeBinding
import br.iesb.mobile.filmes.databinding.FragmentListFilmeBinding
import br.iesb.mobile.filmes.ui.adapter.FilmesAllAdapter
import br.iesb.mobile.filmes.ui.adapter.GenresAdapter
import br.iesb.mobile.filmes.viewmodel.ListfilmeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class DetailFilmeFragment : Fragment() {

    private lateinit var binding: FragmentDetailFilmeBinding
    private val viewModel: ListfilmeViewModel by lazy {
        ViewModelProvider(this).get(ListfilmeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailFilmeBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewFilmes()
        genresFilmes()

    }

    private fun reviewFilmes(){
        val idFilme = arguments?.getInt("id")

        idFilme?.let { viewModel.reviewFilme(it) }
    }

    private fun genresFilmes(){
        val idFilme = arguments?.getInt("id")
        val imagem = arguments?.getString("backdrop_path")

        val recycleListView = binding.rvGenres
        recycleListView.layoutManager = LinearLayoutManager(context)

        viewModel.genresFilms.observe(viewLifecycleOwner) { list ->
            recycleListView.adapter = GenresAdapter(list) {

            }
        }

        idFilme?.let { viewModel.genresFilms(it) }

        imagem?.let {
            Picasso.get().load(StringBuffer("https://image.tmdb.org/t/p/original/").append(imagem)
                .toString()).into(binding.imgPosterPath)
        }
    }

}