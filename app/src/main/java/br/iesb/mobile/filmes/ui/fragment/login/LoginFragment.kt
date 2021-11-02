package br.iesb.mobile.filmes.ui.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.databinding.FragmentLoginBinding
import br.iesb.mobile.filmes.domain.ResultCall
import br.iesb.mobile.filmes.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentLoginBinding
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
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
                    //requireActivity().finish()
                //tela de lista
                 //findNavController().navigate(R.id.action_loginFragment_to_listFilmeFragment)
                    findNavController().navigate(R.id.action_loginFragment_to_posterFragment)
                }
                is ResultCall.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun loginAccount(v: View) {
        viewmodel.loginAccount()
    }

    @Suppress("UNUSED_PARAMETER")
    fun forgotAccount(v: View) {
        findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
    }

    @Suppress("UNUSED_PARAMETER")
    fun signUpAccount(v: View) {
        findNavController().navigate(R.id.action_loginFragment_to_creatAccountFragment)
    }


}