package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import az.sharif.bippyteam.R
import az.sharif.bippyteam.viewmodel.MessageViewModel
import az.sharif.bippyteam.viewmodel.NewsViewModel

class MessageFragment: Fragment(R.layout.fragment_message) {

    private lateinit var viewModel:MessageViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this)[MessageViewModel::class.java]
        var userList = viewModel.message

//        userList.value[0]



    }
}