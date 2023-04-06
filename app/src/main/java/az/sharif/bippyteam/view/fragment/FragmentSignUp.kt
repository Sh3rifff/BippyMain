package az.sharif.bippyteam.view.fragment

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSignUpBinding
import az.sharif.bippyteam.model.MyUsers
import az.sharif.bippyteam.view.activity.MainActivity
import az.sharif.bippyteam.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class FragmentSignUp: Fragment() {
    private val viewModel: UserViewModel by viewModels()
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
        val name = binding.inputName
        val email = binding.inputEmail
        val password = binding.inputPassword
        val confirmPassword = binding.inputConfirmPassword



        /////////// AUTHENTICATION ///////////

        binding.buttonSignUp.setOnClickListener{

            if(name.text.isEmpty()){
                name.error = "Name cannot be empty!"
                name.requestFocus()
                return@setOnClickListener
            }
            if(!nameValidate(name.text.toString())){
                name.error = "Masin nomresi Yaz QAQA!: 00AA000"
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
            val user= MyUsers(0,email.text.toString(), password.text.toString())

            saveUser(user)
            callUserSaved()

            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)

            ///////////////////// FireBase ///////////////////////
//            firebaseAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnSuccessListener {
//                Toast.makeText(requireContext(), "User added", Toast.LENGTH_SHORT).show()
//                val intent = Intent(requireActivity(), MainActivity::class.java)
//                startActivity(intent)
//            }.addOnFailureListener{
//                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
           }
        binding.textSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return binding.root
    }

    private fun callUserSaved() {
        println( viewModel.getAllUsersFromLocal())
    }

    private fun saveUser(myUsers: MyUsers) {
        viewModel.saveUser(myUsers)
    }

    private fun nameValidate(name:String):Boolean{
        val p = Pattern.compile("\\d{2}[A-Z][A-Z]\\d\\d\\d")
        val m = p.matcher(name)
        return m.matches()
    }
}
