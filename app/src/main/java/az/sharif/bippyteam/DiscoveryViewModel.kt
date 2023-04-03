package az.sharif.bippyteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoveryViewModel : ViewModel() {
    private val _servicesLiveData = MutableLiveData<List<ServiceModel>>()
    val servicesLiveData: LiveData<List<ServiceModel>> = _servicesLiveData

    fun getAllServices() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = listOf<ServiceModel>()



            _servicesLiveData.postValue(data)
        }
    }

}