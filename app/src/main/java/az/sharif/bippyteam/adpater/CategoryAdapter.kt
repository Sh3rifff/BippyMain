package az.sharif.bippyteam.adpater

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import az.sharif.bippyteam.databinding.CategoryRecyclerViewItemBinding
import az.sharif.bippyteam.model.CategoryModel
import az.sharif.bippyteam.view.fragment.DiscoveryFragmentDirections

class CategoryAdapter(
    private var categoryModel: List<CategoryModel>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(
        val binding: CategoryRecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryModel.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.motorName.text = categoryModel[position].categoryName
        holder.binding.motorImage.setImageResource(categoryModel[position].categoryImage)
        holder.itemView.setOnClickListener {
            val action = DiscoveryFragmentDirections.discoveryToCategory()
            Navigation.findNavController(it).navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(categorys: List<CategoryModel>) {
        categoryModel = categorys
        notifyDataSetChanged()
    }
}