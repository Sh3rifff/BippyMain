package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.MessageAdapter
import az.sharif.bippyteam.adpater.UserAdapter
import az.sharif.bippyteam.databinding.FragmentMessageBinding
import az.sharif.bippyteam.databinding.FragmentUsersBinding
import az.sharif.bippyteam.viewmodel.MessageViewModel
import az.sharif.bippyteam.viewmodel.UsersViewModel

class UsersFragment: Fragment(R.layout.fragment_users) {

    private lateinit var viewModel:UsersViewModel
    private lateinit var userAdapter: UserAdapter


    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
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


        viewModel= ViewModelProvider(this)[UsersViewModel::class.java]
        viewModel.addUser()

        val list=viewModel.usersList.value

        userAdapter= UserAdapter(list!!)

        binding.usersRecyclerView.layoutManager= LinearLayoutManager(context)
        binding.usersRecyclerView.adapter=userAdapter
        binding.usersRecyclerView.visibility=View.VISIBLE
        binding.progressBar.visibility=View.GONE


    }
}