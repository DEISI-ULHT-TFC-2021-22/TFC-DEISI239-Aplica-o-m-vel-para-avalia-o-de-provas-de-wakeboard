package tfc2022.judgingapp_21800876.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AthleteDao {
    @Insert
    suspend fun insert(fire: AthleteRoom)

    @Query("SELECT * FROM athlete")
    suspend fun getAll(): List<AthleteRoom>
}