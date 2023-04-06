package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.MessageAdapter
import az.sharif.bippyteam.databinding.FragmentMessageBinding
import az.sharif.bippyteam.viewmodel.MessageViewModel
import az.sharif.bippyteam.viewmodel.NewsViewModel

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
        var userList = viewModel.messages

        binding.conversationsRecyclerView.layoutManager= LinearLayoutManager(context)
        binding.conversationsRecyclerView.adapter=messageAdapter










    }
}