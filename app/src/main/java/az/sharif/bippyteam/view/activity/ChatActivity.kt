package az.sharif.bippyteam.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import az.sharif.bippyteam.databinding.ActivityChatBinding
import az.sharif.bippyteam.databinding.ActivityMainBinding


class ChatActivity:AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}