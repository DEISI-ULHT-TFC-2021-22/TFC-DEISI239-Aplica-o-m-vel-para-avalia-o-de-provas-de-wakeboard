package tfc2022.judgingapp_21800876.data.athlete

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AthleteDao {
    @Insert
    suspend fun insert(fire: AthleteRoom)

    @Query("SELECT * FROM athlete")
    suspend fun getAll(): List<AthleteRoom>

    @Query("UPDATE athlete set tricks =:tricks WHERE uuid = :uuid")
    suspend fun updateTricks(uuid: String, tricks : String)

    @Query("UPDATE athlete set score =:score WHERE uuid = :uuid")
    suspend fun updateScore(uuid: String, score : Double)
}