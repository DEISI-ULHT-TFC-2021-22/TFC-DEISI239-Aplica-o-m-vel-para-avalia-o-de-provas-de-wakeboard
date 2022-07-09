package tfc2022.judgingapp_21800876.data.tantrums

data class Tantrum(
    val description: String,
    val id: Int,
    val isManoeuvre: Boolean,
    val isQualifier: Boolean,
    val parent: List<Int>,
    val shortname: String
)