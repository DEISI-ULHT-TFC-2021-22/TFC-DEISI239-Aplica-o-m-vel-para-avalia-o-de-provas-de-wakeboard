package tfc2022.judgingapp_21800876.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class Model(private val dao: AthleteDao) {

    private val raleys = listOf(
        "TS Raley", "Batwing", "Batwing to blind", "90210", "Oh Really",
        "Raley", "Hoochie Glide", "OHH", "Indy Glide", "Nuclear Glide", "Suicide Raley", "911",
        "Blind Judge", "S-Bend", "Vulcan", "Bee sting", "S to blind", "S-Bend ",
        "Krypt", "Hoochie Krypt", "313", "313 5"
    )

    private val tantrums = listOf(
        "Tantrum", "Temper Tantrum",
        "Tantrum to blind", "Moby Dick", "Iro Cross", "Moby Dick 5", "Whirlybird", "Crook", "Whirly 5", "Whirly 7", "WhirlyDick", "Spiderman",
        "Tantrum to fakie", "Billion Dollar Baby",
        "Bel Air", "Bel Air to blind", "Tweetybird"
    )

    private val rolls = listOf(
        "Backroll", "Mexican roll", "Double Backroll",
        "Roll to blind", "KGB", "Wrapped KGB", "KGB 5",
        "Roll to revert", "Half Cab roll", "Mobius", "Blender", "Mobius 5", "Discombobulator",
        "TS Frontroll",
        "Tootsie roll", "Dum dum", "Dum dum 5",
        "Scarecrow", "The Trifecta", "Elephant", "Crow Mobe", "Skeezer", "Crow 5", "Crow 7", "Diesel", "Big worm",
        "Eggroll",
        "TS Backroll", "Double TS Backroll",
        "G-Spot", "Special K", "Blind Pete", "Slurpee",
        "TS Roll to rever", "Pete Rose", "X-Mobe", "Pete Rose 5"
    )

    private val history = mutableListOf<String>()

    val athleteListOfTricks = mutableListOf<String>()

    val leaderboardList = mutableListOf<Athlete>()

    fun getRaleys(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(raleys)
        }
    }

    fun getTantrums(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(tantrums)
        }
    }

    fun getRolls(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(rolls)
        }
    }

    fun getHistory(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(history)
        }
    }

    fun addHistory(trick : String){
        history.add(trick)
    }

    fun addListOfTricks(trick : String){
        athleteListOfTricks.add(trick)
    }

    fun addToLeaderboardList(athlete: Athlete){
        leaderboardList.add(athlete)
    }

    fun addAthlete(athlete : Athlete){
        val athleteRoom = AthleteRoom(athlete_name = athlete.name, age = athlete.age,
            category = athlete.category, frontfoot = athlete.frontfoot, country = athlete.country)

        CoroutineScope(Dispatchers.IO).launch { dao.insert(athleteRoom) }
    }

    fun getAthletes(onFinished: (List<Athlete>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val fires = dao.getAll()
            onFinished(fires.map{
                Athlete(it.athlete_name, it.age, it.category, it.frontfoot, it.country)
            })
        }
    }
}