package com.levox.shopping.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levox.shopping.data.Item
import com.levox.shopping.databinding.ListItemLayoutBinding
import com.levox.shopping.model.ItemViewModel

class ItemListAdapter(
    private val viewModel: ItemViewModel,
    private val openItemDetails: (Item) -> Unit
) : ListAdapter<Item, ItemListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, viewModel: ItemViewModel, openItemDetails: (Item) -> Unit) {
            binding.itemImage.setImageResource(item.imageId)
            binding.itemName.text = item.name

            binding.btnChangeItem.setOnClickListener { openItemDetails(item) }
            binding.btnDeleteItem.setOnClickListener { viewModel.deleteItem(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemLayoutBinding.inflate(LayoutInflater
            .from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, viewModel, openItemDetails)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}