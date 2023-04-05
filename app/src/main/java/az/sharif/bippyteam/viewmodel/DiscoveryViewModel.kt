package az.sharif.bippyteam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.sharif.bippyteam.R
import az.sharif.bippyteam.data.repository.DiscoveryRepository
import az.sharif.bippyteam.model.CategoryModel
import az.sharif.bippyteam.model.ServiceModel
import kotlinx.coroutines.launch

class DiscoveryViewModel : ViewModel() {

    private val repository = DiscoveryRepository()
    private val _services = MutableLiveData<List<ServiceModel>>()
    val services: LiveData<List<ServiceModel>> = _services
    private val _category = MutableLiveData<List<CategoryModel>>()
    val category: LiveData<List<CategoryModel>> = _category

    fun refreshServiceData() {

        val service1 = ServiceModel("Nurgul Motors", "Motorist", R.drawable.nurgun)
        val service2 = ServiceModel("Qəzənfər Evakuasiya", "Evakuasiya", R.drawable.evakuator)
        val service3 = ServiceModel("Tuning Center", "Tuining", R.drawable.tuningcenter)
        val service4 = ServiceModel("Cavid Moyka", "Elektrik", R.drawable.carwashh)
        val service5 = ServiceModel("Nurgul", "Dəmirçi", R.drawable.nurgun)
        val service6 = ServiceModel("Nurgul", "Tuning", R.drawable.nurgun)
        val service7 = ServiceModel("Nurgul", "Moyka", R.drawable.nurgun)
        val service8 = ServiceModel("Nurgul", "Evakuasiya", R.drawable.nurgun)

        val data = arrayListOf(service1, service2,service3,service4,service5,service6,service7,service8)

        _services.value = data
    }

    // When API is ready use this function
    fun getDataFromAPI() {
        viewModelScope.launch {
            _services.value = repository.getServiceResponse()
        }
    }

    fun refreshCategoryData() {

        val category = CategoryModel("Motorist", R.drawable.cp1)
        val category2 = CategoryModel("Slesar", R.drawable.cp2)
        val category3 = CategoryModel("Elektrik", R.drawable.cp3)
        val category4 = CategoryModel("Malyar", R.drawable.filtere)
        val category5 = CategoryModel("Dəmirçi", R.drawable.demirci)
        val category6 = CategoryModel("Sürətlər Qutusu", R.drawable.suretler)
        val category7 = CategoryModel("Tuning", R.drawable.tuning)
        val category8 = CategoryModel("Moyka", R.drawable.moyka)
        val category9 = CategoryModel("Yagdeyisme", R.drawable.oil)
        val category10 = CategoryModel("Evakuasiya", R.drawable.evacuation)
        val category11 = CategoryModel("Ehtiyyat hissələri", R.drawable.carpart)

        val data = arrayListOf(
            category, category2, category3, category4, category5, category6,
            category7, category8, category9, category10, category11
        )

        _category.value = data
    }

    // When API is ready use this function
    fun getDataFromAPIs() {
        viewModelScope.launch {
            _category.value = repository.getCategoryResponse()
        }
    }
}