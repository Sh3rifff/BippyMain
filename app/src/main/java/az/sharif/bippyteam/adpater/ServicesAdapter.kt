package az.sharif.bippyteam.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.model.ServiceModel
import az.sharif.bippyteam.databinding.ServisRecyclerRowItemBinding

class ServicesAdapter(
    private var serviceModels: List<ServiceModel>
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding =
            ServisRecyclerRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {

        holder.binding.serviceName.text = serviceModels[position].name
        holder.binding.serviceCategory.text = serviceModels[position].category
        holder.binding.serviceImage.setImageResource(serviceModels[position].image)


//        val model = serviceModels?.get(position)
//        holder.binding.serviceCategory.text = model?.category
//        model?.image?.let {
//            holder.binding.serviceImage.setImageResource(it)
//        }

    }

    override fun getItemCount(): Int = serviceModels.size

    class ServiceViewHolder(
        val binding: ServisRecyclerRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(services: List<ServiceModel>) {
        serviceModels = services
        notifyDataSetChanged()
    }
}