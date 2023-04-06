package az.sharif.bippyteam.data.repository

import az.sharif.bippyteam.model.CategoryModel
import az.sharif.bippyteam.model.ServiceModel
import az.sharif.bippyteam.service.RetrofitHelper

class DiscoveryRepository {

    private val service = RetrofitHelper.service
    private val category = RetrofitHelper.category

    suspend fun getServiceResponse(): List<ServiceModel> {
        val response = service.getServiceData()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }

    suspend fun getCategoryResponse(): List<CategoryModel> {
        val response = category.getCategoryData()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}