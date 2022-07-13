package tfc2022.judgingapp_21800876.data.athlete

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Athlete(
    var name: String,
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
    var score: Double,
) : Parcelable