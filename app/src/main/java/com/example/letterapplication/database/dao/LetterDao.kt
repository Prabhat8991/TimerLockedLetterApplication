package com.example.letterapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.letterapplication.database.DatabaseLetterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {
    @Query("SELECT * FROM letter_db")
    fun getLetter(): LiveData<List<DatabaseLetterModel>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(databaseLetterModel: DatabaseLetterModel): Long

    @Query("UPDATE letter_db SET isLocked = :isLocked WHERE id =:id")
    suspend fun update(isLocked: Boolean, id: Int)
}