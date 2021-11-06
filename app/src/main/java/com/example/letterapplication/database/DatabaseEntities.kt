package com.example.letterapplication.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.letterapplication.database.dao.LetterDao
import com.example.letterapplication.model.LetterUIModel

@Entity(
  tableName = "letter_db"
)
data class DatabaseLetterModel (
  @PrimaryKey(autoGenerate = true) val id: Int,
  val isLocked: Boolean,
  val title: String,
  val description: String,
  val timeStamp: String
)
@Database(entities = [DatabaseLetterModel::class], version = 1)
abstract class LetterDatabase: RoomDatabase() {
  abstract fun LetterDao(): LetterDao
}

fun List<DatabaseLetterModel>.asUiModel(): List<LetterUIModel> {
  return map {
    LetterUIModel(
      title = it.title,
      description = it.description,
      timeStamp = it.timeStamp
    )
  }
}