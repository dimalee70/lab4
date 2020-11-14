package kz.lab4.data

import android.content.Context
import androidx.room.*
import kz.lab4.data.dao.TaskDao
import kz.lab4.entity.todo.TaskRoom

@Database(
    entities = [
        TaskRoom::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase(){

    abstract fun getTaskDao(): TaskDao

    companion object{

        @Volatile
        private lateinit var INSTANCE: DB

        fun getDatabase(context: Context): DB {
            synchronized(DB::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DB::class.java, "lab4_kz"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE
        }
    }
}