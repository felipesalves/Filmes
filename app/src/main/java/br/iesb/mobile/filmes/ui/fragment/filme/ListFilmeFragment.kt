package br.iesb.mobile.filmes.ui.fragment.filme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import br.iesb.mobile.filmes.databinding.FragmentListFilmeBinding
import br.iesb.mobile.filmes.ui.adapter.FilmesAllAdapter
import br.iesb.mobile.filmes.ui.adapter.FilmesLancamentosAdapter
import br.iesb.mobile.filmes.viewmodel.ListfilmeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class ListFilmeFragment : Fragment() {

    private lateinit var binding: FragmentListFilmeBinding
    private val viewModel: ListfilmeViewModel by lazy {
        ViewModelProvider(this).get(ListfilmeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListFilmeBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewFilmes()
        recycleViewAllFilmes()
    }

    @SuppressLint("WrongConstant")
    private fun recycleViewFilmes(){
        val recycleListView = binding.rvRelease
        recycleListView.layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)

        viewModel.listPopularFilms.observe(viewLifecycleOwner) { list ->
            recycleListView.adapter = FilmesLancamentosAdapter(list) {

                var bundle = bundleOf(
                    "id" to it.id,
                    "backdrop_path" to it.backdrop_path
                )

                //findNavController().navigate(R.id.action_listFilmeFragment_to_detailFilmeFragment, bundle)


            }
        }

        viewModel.listPopularFilms()
    }

    @SuppressLint("WrongConstant")
    private fun recycleViewAllFilmes(){
        val recycleListView = binding.rvAllFilmes
        recycleListView.layoutManager = LinearLayoutManager(context)

        viewModel.listAllFilms.observe(viewLifecycleOwner) { list ->
            recycleListView.adapter = FilmesAllAdapter(list) {

                var bundle = bundleOf(
                    "id" to it.id,
                    "backdrop_path" to it.backdrop_path
                )

             //   findNavController().navigate(R.id.action_listFilmeFragment_to_detailFilmeFragment, bundle)

            }
        }

        viewModel.listAllFilms(1)
    }





}