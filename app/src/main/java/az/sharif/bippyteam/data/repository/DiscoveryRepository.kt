package az.sharif.bippyteam.data.repository

import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.ServiceModel

class DiscoveryRepository {

    suspend fun getServices(): List<ServiceModel> {
        return listOf(
            ServiceModel("Nurgul", "Service", R.drawable.nurgun),
            ServiceModel("Nurgul", "Service", R.drawable.nurgun)
        )
    }
}