package az.sharif.bippyteam.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSignUpBinding
import az.sharif.bippyteam.view.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FragmentSignUp: Fragment() {
    private lateinit var binding :FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        var name = binding.inputName
        var email = binding.inputEmail
        var password = binding.inputPassword
        var confirmPassword = binding.inputConfirmPassword

        /////////// AUTHENTICATION ///////////

        binding.buttonSignUp.setOnClickListener{

            if(name.text.isEmpty()){
                name.error = "Name cannot be empty!"
                name.requestFocus()
                return@setOnClickListener
            }
            if(email.text.isEmpty()){
                email.requestFocus()
                email.error = "Email cannot be empty"
                return@setOnClickListener

            }
            if(password.text.isEmpty()|| password.text.length<5){
                password.requestFocus()
                password.error = "Password must contain at least 6 character"
                return@setOnClickListener

            }
            if(confirmPassword.text.isEmpty() || confirmPassword.text.toString()!=password.text.toString()){
                confirmPassword.requestFocus()
                confirmPassword.error = "Did you already forget your password?"
                return@setOnClickListener

            }
            firebaseAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnSuccessListener {
                Toast.makeText(requireContext(), "User added", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Error 404", Toast.LENGTH_SHORT).show()
            }



        }
        binding.textSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return binding.root
    }
}