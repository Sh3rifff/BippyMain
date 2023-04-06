package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import az.sharif.bippyteam.R
import az.sharif.bippyteam.databinding.FragmentServicePageBinding
import az.sharif.bippyteam.model.ServiceModel

class ServicePageFragment : Fragment(R.layout.fragment_service_page) {

    private lateinit var binding: FragmentServicePageBinding
    private lateinit var newsUuid: ServiceModel
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentServicePageBinding.bind(view)
        navController = Navigation.findNavController(view)

        arguments?.let {
            newsUuid = ServicePageFragmentArgs.fromBundle(it).service
            binding.serviceFragmentImage.setImageResource(newsUuid.image)
            binding.collapsingToolbar.title = newsUuid.name

        }

        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }

    }


}