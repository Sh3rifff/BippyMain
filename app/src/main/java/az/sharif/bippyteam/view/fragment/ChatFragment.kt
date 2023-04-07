package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentChatBinding
import az.sharif.bippyteam.databinding.FragmentMessageBinding
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar

class ChatFragment: Fragment(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var userName:String
    private lateinit var userImage:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageBack.setOnClickListener(){
            Navigation.findNavController(it).popBackStack()
        }


        arguments?.let {
            userName=ChatFragmentArgs.fromBundle(it).name
            userImage=ChatFragmentArgs.fromBundle(it).image
        }

        binding.textName.text=userName
        binding.imageProfile.downloadFromUrl(userImage, placeHolderProgressBar(view.context))






    }
}