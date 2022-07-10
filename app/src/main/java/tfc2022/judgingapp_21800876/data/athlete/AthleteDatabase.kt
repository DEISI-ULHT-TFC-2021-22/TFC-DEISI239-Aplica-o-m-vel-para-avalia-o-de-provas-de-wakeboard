package tfc2022.judgingapp_21800876.data.athlete

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AthleteRoom::class], version = 1)
abstract class AthleteDatabase : RoomDatabase() {

    abstract fun athleteDao() : AthleteDao

    companion object {

        private var instance: AthleteDatabase? = null

        fun getInstance(applicationContext: Context): AthleteDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        AthleteDatabase::class.java,
                        "athlete_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance as AthleteDatabase
            }
        }
    }
}