package tfc2022.judgingapp_21800876.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Athlete(
    var name: String,
    var age: String,
    var category: String,
    var frontfoot: String,
    var country: String,
) : Parcelable