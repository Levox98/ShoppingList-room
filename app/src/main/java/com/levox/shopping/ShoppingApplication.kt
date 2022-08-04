package com.levox.shopping

import android.app.Application
import com.levox.shopping.data.ItemDatabase

class ShoppingApplication : Application() {
    val database: ItemDatabase by lazy { ItemDatabase.getDatabase(this) }
}