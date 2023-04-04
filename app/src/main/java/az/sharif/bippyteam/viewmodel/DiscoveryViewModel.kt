package az.sharif.bippyteam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.ServiceModel

class DiscoveryViewModel : ViewModel() {

    private val _services = MutableLiveData<List<ServiceModel>>()
    val services:LiveData<List<ServiceModel>> = _services

    fun refreshData() {

        val service1 = ServiceModel("Nurgul", "Service", R.drawable.nurgun)
        val service2 = ServiceModel("Nurgul", "Service", R.drawable.nurgun)

        val data = arrayListOf(service1, service2)

        _services.value = data

    }

}