package br.iesb.mobile.filmes.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.databinding.FragmentCreatAccountBinding
import br.iesb.mobile.filmes.domain.ResultCall
import br.iesb.mobile.filmes.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreatAccountBinding
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatAccountBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewmodel = viewmodel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.result.observe(viewLifecycleOwner) {

            when (it) {
                is ResultCall.Success -> {
                    requireActivity().finish()
                    //tela de lista
                    // findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }
                is ResultCall.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    @Suppress("UNUSED_PARAMETER")
    fun createAccount(v: View) {
        viewmodel.signUpAccount()
    }

}