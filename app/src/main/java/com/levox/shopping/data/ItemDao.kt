package com.levox.shopping.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Query("DELETE FROM item")
    suspend fun deleteAllItems()
}