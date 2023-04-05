package az.sharif.bippyteam.data.repository

import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.ServiceModel
import az.sharif.bippyteam.service.RetrofitHelper

class DiscoveryRepository {

    private val service = RetrofitHelper.service

    suspend fun getServices(): List<ServiceModel> {
        return listOf(
            ServiceModel("Nurgul", "Service", R.drawable.nurgun),
            ServiceModel("Nurgul", "Service", R.drawable.nurgun)
        )
    }

    suspend fun getServiceResponse(): List<ServiceModel> {
        val response = service.getData()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}