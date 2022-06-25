package tfc2022.judgingapp_21800876.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    var athleteListOfTricks = ""

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
        athleteListOfTricks += "$trick,"
    }

    fun addAthlete(athlete : Athlete){
        val athleteRoom = AthleteRoom(athlete_name = athlete.name, age = athlete.age,
            category = athlete.category, frontfoot = athlete.frontfoot, country = athlete.country,
        tricks = "", fall = false, execution = "", intensity = "", comprehension = "",
        score = 0.0)

        CoroutineScope(Dispatchers.IO).launch { dao.insert(athleteRoom) }
    }

    fun getAthletes(onFinished: (List<Athlete>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            onFinished(athletes.map{
                Athlete(it.athlete_name, it.age, it.category, it.frontfoot, it.country,
                it.tricks, it.fall, it.execution, it.intensity, it.comprehension, it.score)
            })
        }
    }

    fun updateTricks(tricks : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateTricks(athlete.uuid, tricks)
                }
            }
        }
    }

    fun updateExecution(execution : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateExecution(athlete.uuid, execution)
                }
            }
        }
    }

    fun updateIntensity(intensity : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateIntensity(athlete.uuid, intensity)
                }
            }
        }
    }

    fun updateComprehension(comprehension : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateComprehension(athlete.uuid, comprehension)
                }
            }
        }
    }

    fun updateScore(score : Double, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateScore(athlete.uuid, score)
                }
            }
        }
    }
}