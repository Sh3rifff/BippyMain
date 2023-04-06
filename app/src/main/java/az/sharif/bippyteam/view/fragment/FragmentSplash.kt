package az.sharif.bippyteam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSplashBinding
import az.sharif.bippyteam.view.activity.MainActivity
import az.sharif.bippyteam.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class FragmentSplash:Fragment() {


    private lateinit var binding: FragmentSplashBinding
    private val userViewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSplashBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lifecycleScope.launch {
            userViewModel.autoLogin()
        }


        binding.startButton.setOnClickListener {
            userViewModel.autoLog.observe(viewLifecycleOwner){
                if (it){
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                }
            }

        }

        return binding.root
    }
}