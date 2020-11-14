package kz.lab4.entity.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
open class TaskRoom (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "duration")
    val duration: Int
)