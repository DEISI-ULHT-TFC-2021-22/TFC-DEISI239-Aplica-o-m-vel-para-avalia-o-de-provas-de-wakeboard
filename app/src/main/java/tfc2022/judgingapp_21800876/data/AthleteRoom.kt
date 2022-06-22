package tfc2022.judgingapp_21800876.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "athlete")
data class AthleteRoom(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "athlete") val athlete_name: String,
    var age: String,
    var category: String,
    var frontfoot: String,
    var country: String,
)