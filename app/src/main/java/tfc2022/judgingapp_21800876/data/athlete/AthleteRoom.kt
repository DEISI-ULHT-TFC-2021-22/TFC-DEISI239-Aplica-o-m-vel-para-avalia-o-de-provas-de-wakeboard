package tfc2022.judgingapp_21800876.data.athlete

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//Athlete DAO class, that holds information and keeps data persistent

@Entity(tableName = "athlete")
data class AthleteRoom(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "athlete") val athlete_name: String,
    var age: String,
    var category: String,
    var frontfoot: String,
    var country: String,
    var tricks: String,
    var grabs: String,
    var fall: Boolean,
    var execution: String,
    var intensity: String,
    var composition: String,
    var score: Double
)