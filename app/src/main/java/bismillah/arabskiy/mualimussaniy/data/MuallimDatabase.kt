package bismillah.arabskiy.mualimussaniy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bismillah.arabskiy.mualimussaniy.data.dao.ArticlesDao
import bismillah.arabskiy.mualimussaniy.data.model.Article

@Database(entities = [Article::class], version = 1)

abstract class MuallimDatabase: RoomDatabase() {

    companion object {
        private lateinit var INSTANCE : MuallimDatabase

        fun getInstance(context: Context): MuallimDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context,
                    MuallimDatabase::class.java,"Muallim-kitabi")
                    .createFromAsset("Muallim-kitabi.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
    abstract fun articleDao(): ArticlesDao
}