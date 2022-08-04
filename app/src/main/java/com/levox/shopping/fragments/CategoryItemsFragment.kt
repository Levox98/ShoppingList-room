package com.levox.shopping.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.levox.shopping.R
import com.levox.shopping.ShoppingApplication
import com.levox.shopping.adapters.CategoryAdapter
import com.levox.shopping.data.DataSource
import com.levox.shopping.data.SourceItem
import com.levox.shopping.databinding.FragmentCategoryItemsBinding
import com.levox.shopping.model.ItemViewModel
import com.levox.shopping.model.ItemViewModelFactory

class CategoryItemsFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels {
        ItemViewModelFactory(
            (activity?.application as ShoppingApplication).database.itemDao()
        )
    }

    private lateinit var category: String

    private var _binding: FragmentCategoryItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            category = it.getString("category").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryItemsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryAdapter(returnChosenCategory(category)) {
            viewModel.addItem(it.name, it.imageId)
            val action = CategoryItemsFragmentDirections.actionCategoryItemsFragmentToItemListFragment()
        }

        binding.listRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun returnChosenCategory(categoryName: String): List<SourceItem> {
        return when (categoryName) {
            requireContext().getString(R.string.ctg_meat) -> DataSource.meatList
            requireContext().getString(R.string.ctg_fruit_and_veg) -> DataSource.fruitAndVeggiesList
            requireContext().getString(R.string.ctg_sweet) -> DataSource.sweetsList
            requireContext().getString(R.string.ctg_tea_coffee_cocoa) -> DataSource.coffeeTeaList
            requireContext().getString(R.string.ctg_groceries) -> DataSource.groceriesList
            requireContext().getString(R.string.ctg_frozen) -> DataSource.frozenList
            requireContext().getString(R.string.ctg_dairy) -> DataSource.dairyList
            requireContext().getString(R.string.ctg_sea) -> DataSource.seaProductsList
            requireContext().getString(R.string.ctg_bread) -> DataSource.breadList
            requireContext().getString(R.string.ctg_drinks) -> DataSource.drinksList
            requireContext().getString(R.string.ctg_chemistry) -> DataSource.chemistryList
            else -> throw IllegalArgumentException("Category not found")
        }
    }
}