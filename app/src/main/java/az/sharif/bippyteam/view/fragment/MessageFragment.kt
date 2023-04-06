package az.sharif.bippyteam.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.MessageAdapter
import az.sharif.bippyteam.databinding.FragmentMessageBinding
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import az.sharif.bippyteam.viewmodel.MessageViewModel

class MessageFragment: Fragment(R.layout.fragment_message) {

    private lateinit var viewModel:MessageViewModel
    private lateinit var messageAdapter:MessageAdapter



    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel= ViewModelProvider(this)[MessageViewModel::class.java]
        viewModel.addUser()

        val list=viewModel.messages.value

        messageAdapter=MessageAdapter(list!!)

        binding.conversationsRecyclerView.layoutManager= LinearLayoutManager(context)
        binding.conversationsRecyclerView.adapter=messageAdapter
        binding.conversationsRecyclerView.visibility=View.VISIBLE
        binding.progressBar.visibility=View.GONE


        //current user details
        binding.textName.text="Nurlan"
        binding.imageProfile.downloadFromUrl("https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
            placeHolderProgressBar(view.context)
        )

    }
}