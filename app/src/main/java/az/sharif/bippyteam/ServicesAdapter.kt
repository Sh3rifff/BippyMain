package az.sharif.bippyteam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.databinding.ServisRecyclerRowItemBinding

class ServicesAdapter : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    private val serviceModels: List<ServiceModel>? = null

    class ServiceViewHolder(
        val binding: ServisRecyclerRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding =
            ServisRecyclerRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.binding.serviceName.text = serviceModels!![position].name
        holder.binding.serviceCategory.text = serviceModels[position].category
        holder.binding.serviceImage.setImageResource(serviceModels[position].image)
    }

    override fun getItemCount(): Int = serviceModels!!.size
}