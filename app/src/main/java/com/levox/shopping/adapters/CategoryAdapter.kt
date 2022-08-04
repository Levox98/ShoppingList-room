package com.levox.shopping.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levox.shopping.data.Item
import com.levox.shopping.data.SourceItem
import com.levox.shopping.databinding.ItemInCategoryLayoutBinding
import com.levox.shopping.model.ItemViewModel

class CategoryAdapter(
    private val sourceItemList: List<SourceItem>,
    private val onAddItemClicked: (Item) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(private val binding: ItemInCategoryLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(sourceItem: SourceItem, onAddItemClicked: (Item) -> Unit) {
                binding.itemImage.setImageResource(sourceItem.imageId)
                binding.itemName.text = sourceItem.name

                binding.btnAddItem.setOnClickListener {
                    onAddItemClicked(Item(name = sourceItem.name, imageId = sourceItem.imageId))
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemInCategoryLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = sourceItemList[position]
        holder.bind(current, onAddItemClicked)
    }

    override fun getItemCount(): Int {
        return sourceItemList.size
    }
}