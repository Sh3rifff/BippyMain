package az.sharif.bippyteam.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import az.sharif.bippyteam.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpViewModel(application: Application):AndroidViewModel(application) {

     fun pickPhoto(context: Context,activity: Activity, imagePicker: ActivityResultLauncher<Intent>){
        if(ContextCompat.checkSelfPermission(context,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePicker.launch(galleryIntent)

        }
    }


    fun signUpChecker(name: EditText, email: EditText, password: EditText, confirmPassword: EditText) {
        if(name.text.isEmpty()){
            name.error = "Name cannot be empty!"
            name.requestFocus()
            return
        }
        if(!nameValidate(name.text.toString())){
            name.error = "Masin nomresi Yaz QAQA!: 00AA000"
            name.requestFocus()
            return
        }
        if(email.text.isEmpty()){
            email.requestFocus()
            email.error = "Email cannot be empty"
            return

        }
        if(password.text.isEmpty()|| password.text.length<5){
            password.requestFocus()
            password.error = "Password must contain at least 6 character"
            return

        }
        if(confirmPassword.text.isEmpty() || confirmPassword.text.toString()!=password.text.toString()){
            confirmPassword.requestFocus()
            confirmPassword.error = "Did you already forget your password?"
            return

        }


    }

    fun pickerInside(photo: Uri?, requireActivity:Activity,binding:FragmentSignUpBinding): Bitmap {


        val inputStream= requireActivity.contentResolver.openInputStream(photo!!)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        binding.imageProfile.setImageBitmap(bitmap)
        binding.textAddImage.visibility = View.GONE

        return bitmap
    }



    private fun nameValidate(name:String):Boolean{
        val p = Pattern.compile("\\d{2}[A-Z][A-Z]\\d\\d\\d")
        val m = p.matcher(name)
        return m.matches()
    }




}