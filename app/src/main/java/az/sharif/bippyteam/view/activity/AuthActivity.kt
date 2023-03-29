package az.sharif.bippyteam.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding :ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}