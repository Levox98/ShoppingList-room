package com.levox.shopping.model


import androidx.lifecycle.*
import com.levox.shopping.data.Item
import com.levox.shopping.data.ItemDao
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemDao.getAllItems().asLiveData()

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewEntry(
        itemName: String,
        itemImageId: Int
    ): Item {
        return Item(name = itemName, imageId = itemImageId)
    }

    fun addItem(itemName: String, itemImageId: Int) {
        val item = getNewEntry(itemName, itemImageId)
        insertItem(item)
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun clearList() {
        viewModelScope.launch {
            itemDao.deleteAllItems()
        }
    }

    fun isListValid(): Boolean {
        return itemDao.getAllItems().asLiveData().value.isNullOrEmpty()
    }
}

class ItemViewModelFactory(private val itemDao: ItemDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return ItemViewModel(itemDao) as T
        }
        throw IllegalArgumentException("No such ViewModel class")
    }
}