package tfc2022.judgingapp_21800876

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.data.athlete.AthleteDao
import tfc2022.judgingapp_21800876.data.athlete.AthleteRoom
import java.util.*

class Model(private val dao: AthleteDao) {

    private val history = mutableListOf<String>()

    var athleteListOfTricks = ""

    private var athletesList : List<AthleteRoom> = emptyList()

    fun getHistory(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(history)
        }
    }

    fun addHistory(trick : String){
        history.add(trick)
    }

    fun addListOfTricks(trick: String, trickHeight: String, trickWave: String){
        athleteListOfTricks += "$trick ($trickHeight, $trickWave), "
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
            athletesList = athletes

            Collections.sort(athletes, Comparator<AthleteRoom> { obj1, obj2 ->
                obj2.score.compareTo(obj1.score)
            })

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

    private fun convertAthletes() : List<Athlete>{
        return athletesList.map { Athlete(it.athlete_name, it.age, it.category, it.frontfoot, it.country,
            it.tricks, it.fall, it.execution, it.intensity, it.comprehension, it.score) }
    }

    fun getAllAthletesList() : List<Athlete>{
        return convertAthletes()
    }
}