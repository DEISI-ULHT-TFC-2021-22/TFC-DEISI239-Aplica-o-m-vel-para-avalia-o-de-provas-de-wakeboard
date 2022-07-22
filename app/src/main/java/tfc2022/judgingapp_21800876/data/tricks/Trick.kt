package tfc2022.judgingapp_21800876.data.tricks

// Trick class, used for the JSON file of all the tricks

data class Trick(
    val description: String,
    val id: Int,
    val isManoeuvre: Boolean,
    val isQualifier: Boolean,
    val parent: List<Int>,
    val shortname: String
)