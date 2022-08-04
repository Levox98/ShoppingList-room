package com.levox.shopping.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.levox.shopping.R
import com.levox.shopping.ShoppingApplication
import com.levox.shopping.adapters.ItemListAdapter
import com.levox.shopping.data.Item
import com.levox.shopping.databinding.FragmentItemListBinding
import com.levox.shopping.model.ItemViewModel
import com.levox.shopping.model.ItemViewModelFactory

class ItemListFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels {
        ItemViewModelFactory(
            (activity?.application as ShoppingApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListAdapter(viewModel) {
            val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment()
            this.findNavController().navigate(action)
        }

        binding.listRecyclerView.adapter = adapter
        binding.listRecyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.btnClearList.apply {
            isEnabled = viewModel.isListValid()
            setOnClickListener { viewModel.clearList() }
        }

        binding.btnAddItem.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToCategoryFragment()
            this.findNavController().navigate(action)
        }

        viewModel.allItems.observe(this.viewLifecycleOwner) { itemList ->
            itemList.let {
                adapter.submitList(it)
            }
        }
    }
}