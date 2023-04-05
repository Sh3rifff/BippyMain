package az.sharif.bippyteam.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import az.sharif.bippyteam.R
import az.sharif.bippyteam.adpater.ServicesAdapter
import az.sharif.bippyteam.databinding.FragmentCategoryPageBinding
import az.sharif.bippyteam.viewmodel.DiscoveryViewModel


class CategoryPageFragment : Fragment(R.layout.fragment_category_page) {

    private lateinit var binding: FragmentCategoryPageBinding
    private val servicesAdapter = ServicesAdapter(arrayListOf())
    private val viewModel: DiscoveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryPageBinding.bind(view)

        viewModel.refreshServiceData()
        binding.categoryItemRecycler.layoutManager = LinearLayoutManager(context)
        binding.categoryItemRecycler.adapter = servicesAdapter
        observeServiceData()

    }

    private fun observeServiceData() {
        viewModel.services.observe(viewLifecycleOwner) { services ->

            services?.let {
                binding.categoryItemRecycler.layoutManager = LinearLayoutManager(context)
                servicesAdapter.submitList(services)
            }
        }
    }

}