package az.sharif.bippyteam.view.fragment

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentSignUpBinding
import az.sharif.bippyteam.model.MyUsers
import az.sharif.bippyteam.view.activity.MainActivity
import az.sharif.bippyteam.viewmodel.SignUpViewModel
import az.sharif.bippyteam.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class FragmentSignUp: Fragment() {
    /////////////Declaration////////////////////
    private val viewModel: UserViewModel by viewModels()
    private val signUpViewModel:SignUpViewModel by viewModels()
    private lateinit var binding :FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var photo : Uri?=null
    private var bitmap: Bitmap?=null
    private lateinit var imagePicker: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
        imagePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            photo = it.data?.data
            bitmap=signUpViewModel.pickerInside(photo,requireActivity(),binding)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.imageProfile.setOnClickListener{
            signUpViewModel.pickPhoto(requireContext(),requireActivity(),imagePicker)
        }
        /////////// AUTHENTICATION ///////////
        binding.buttonSignUp.setOnClickListener{
            signUpViewModel.signUpChecker(binding.inputName,binding.inputEmail,binding.inputPassword,binding.inputConfirmPassword)
            debug(binding.inputEmail,binding.inputPassword)
           }
        binding.textSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        return binding.root
    }

    private fun debug(email: EditText, password: EditText) {
        val user= MyUsers(0,email.text.toString(), password.text.toString())
        saveUser(user)
        callUserSaved()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }
    private fun callUserSaved() { println( viewModel.getAllUsersFromLocal()) }
    private fun saveUser(myUsers: MyUsers) { viewModel.saveUser(myUsers) }
}
