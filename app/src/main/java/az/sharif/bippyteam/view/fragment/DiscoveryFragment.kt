package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.ServicesAdapter
import az.sharif.bippyteam.databinding.FragmentDiscoveryBinding
import az.sharif.bippyteam.viewmodel.DiscoveryViewModel

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {

    private val servicesAdapter = ServicesAdapter(arrayListOf())
    private lateinit var binding: FragmentDiscoveryBinding
    private val viewModel: DiscoveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDiscoveryBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        binding.serviceRecycler.layoutManager = LinearLayoutManager(context)
        binding.serviceRecycler.adapter = servicesAdapter

        observeData()
    }

    private fun observeData() {
        viewModel.services.observe(viewLifecycleOwner) { services ->

            services?.let {
                binding.serviceRecycler.layoutManager = LinearLayoutManager(context)
                servicesAdapter.submitList(services)
            }

        }
    }
}
