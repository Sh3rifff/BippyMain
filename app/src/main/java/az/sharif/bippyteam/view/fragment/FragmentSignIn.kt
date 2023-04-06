package az.sharif.bippyteam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSignInBinding
import az.sharif.bippyteam.view.activity.MainActivity
import az.sharif.bippyteam.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmentSignIn:Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding:FragmentSignInBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.createAccount.setOnClickListener{ findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }

       // binding.Debug.setOnClickListener{ userViewModel.getAllUsersFromLocal() }

        val email = binding.inputEmail
        val password  = binding.inputPassword

        binding.buttonSignIn.setOnClickListener{ buttonPressedAction(email, password) }

        return binding.root

    }

    private fun buttonPressedAction(email: EditText, password: EditText) {
        if(email.text.isEmpty()){
            email.error="Pls enter email!"
            email.requestFocus()
            return
        }
        if(password.text.isEmpty()){
            password.error="Enter password"
            password.requestFocus()
            return
        }
        binding.progressBar.isVisible=true
        userViewModel.getUserFromLocal(email.text.toString(),password.text.toString())

        startMainActivity()
    }

    private fun startMainActivity() {
        userViewModel.identify.observe(viewLifecycleOwner){
            if(it){
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}