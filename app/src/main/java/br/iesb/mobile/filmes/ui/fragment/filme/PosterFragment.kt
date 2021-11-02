package br.iesb.mobile.filmes.ui.fragment.filme

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.base.BaseFragment
import br.iesb.mobile.filmes.databinding.FragmentPosterBinding
import br.iesb.mobile.filmes.databinding.ItemPosterBinding
import br.iesb.mobile.filmes.domain.filmes.Poster
import br.iesb.mobile.filmes.ui.adapter.PosterAdapter
import br.iesb.mobile.filmes.util.PagingLoadStateAdapter
import br.iesb.mobile.filmes.viewmodel.PosterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PosterFragment : BaseFragment<FragmentPosterBinding, PosterViewModel>(),
    PosterAdapter.CharacterClickListener{

    private val posterViewModel: PosterViewModel by viewModels()

    @Inject
    lateinit var posterAdapter: PosterAdapter

    override val layoutId: Int
        get() = R.layout.fragment_poster

    override fun getVM(): PosterViewModel = posterViewModel

    override fun bindVM(binding: FragmentPosterBinding, vm: PosterViewModel) =
        with(binding) {
            with(posterAdapter) {
                rvCharacters.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                rvCharacters.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )

                swipeRefresh.setOnRefreshListener { refresh() }
                characterClickListener = this@PosterFragment

                with(vm) {
                    launchOnLifecycleScope {
                        PosterFragment.collectLatest { submitData(it) }
                    }
                    launchOnLifecycleScope {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }
                }
            }
        }

    override fun onCharacterClicked(binding: ItemPosterBinding, poster: Poster) {
       /* val extras = FragmentNavigatorExtras(
            binding.ivAvatar to "avatar_${poster.id}",
            binding.tvName to "name_${poster.id}",

        )*/

        var bundle = bundleOf(
            "id" to poster.id,
            "backdrop_path" to poster.backdrop_path
        )

        findNavController().navigate(R.id.action_posterFragment_to_detailFilmeFragment, bundle)

        /*findNavController().navigate(
            CharactersFragmentDirections.actionCharactersToCharacterDetail(character),
            extras
        )*/
    }

}