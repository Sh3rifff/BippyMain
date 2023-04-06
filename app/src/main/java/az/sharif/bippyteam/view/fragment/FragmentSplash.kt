package az.sharif.bippyteam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSplashBinding
import az.sharif.bippyteam.view.activity.MainActivity
import az.sharif.bippyteam.viewmodel.UserViewModel

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
    ): View {


            userViewModel.autoLogin()



        binding.startButton.setOnClickListener {
            userViewModel.getAllUsersFromLocal()
            userViewModel.autoLog.observe(viewLifecycleOwner){
//                Log.d("bla", "onCreateView: $it")

                if (!it){
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                }
            }

        }
        binding.imageLogo.setOnClickListener {
            userViewModel.clearAll()
            userViewModel.autoLogin()
        }

        return binding.root
    }
}