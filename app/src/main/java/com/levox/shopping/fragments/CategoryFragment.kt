package com.levox.shopping.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.levox.shopping.ShoppingApplication
import com.levox.shopping.databinding.FragmentCategoryBinding
import com.levox.shopping.model.ItemViewModel
import com.levox.shopping.model.ItemViewModelFactory

class CategoryFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels {
        ItemViewModelFactory(
            (activity?.application as ShoppingApplication).database.itemDao()
        )
    }

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            categoryFragment = this@CategoryFragment
        }
    }

    fun goToSelectedCategory(categoryName: String) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryItemsFragment(categoryName)
        this.findNavController().navigate(action)
    }
}