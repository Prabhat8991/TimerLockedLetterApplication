package com.example.letterapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.letterapplication.database.DatabaseLetterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {
    @Query("SELECT * FROM letter_db")
    fun getLetter(): LiveData<List<DatabaseLetterModel>>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg databaseLetterModel: DatabaseLetterModel)
}