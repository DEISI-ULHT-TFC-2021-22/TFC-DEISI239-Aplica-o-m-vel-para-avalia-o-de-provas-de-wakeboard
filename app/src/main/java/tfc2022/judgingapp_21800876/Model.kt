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

    var athleteListOfGrabs = ""

    private var athletesList : List<AthleteRoom> = emptyList()

    fun getHistory(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(history)
        }
    }

    fun addHistory(trick : String){
        history.add(trick)
    }

    fun addListOfTricks(trick: String, trickHeight: String, trickWave: String, stats : String){
        athleteListOfTricks += if(stats.length == 0 && trick != "Fall") {
            "$trick ($trickHeight, $trickWave), "
        }else if(trick == "Fall"){
            "Fall, "
        }else{
            "$trick ($trickHeight, $trickWave) + ($stats), "
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

    fun addListOfGrabs(grab: String){
        athleteListOfGrabs += "$grab, "
    }

    fun updateGrabs(grabs : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateGrabs(athlete.uuid, grabs)
                }
            }
        }
    }

    fun addAthlete(athlete : Athlete){
        val athleteRoom = AthleteRoom(athlete_name = athlete.name, age = athlete.age,
            category = athlete.category, frontfoot = athlete.frontfoot, country = athlete.country,
        tricks = "", grabs = "", fall = false, execution = "", intensity = "", composition = "",
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
                it.tricks, it.grabs, it.fall, it.execution, it.intensity, it.composition, it.score)
            })
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

    fun updateStats(execution : String, intensity : String, composition : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            val athletes = dao.getAll()
            for(athlete in athletes){
                if(athlete.athlete_name == name){
                    dao.updateExecution(athlete.uuid, execution)
                    dao.updateIntensity(athlete.uuid, intensity)
                    dao.updateComposition(athlete.uuid, composition)
                }
            }
        }
    }

    private fun convertAthletes() : List<Athlete>{
        return athletesList.map { Athlete(it.athlete_name, it.age, it.category, it.frontfoot, it.country,
            it.tricks, it.grabs, it.fall, it.execution, it.intensity, it.composition, it.score) }
    }

    fun getAllAthletesList() : List<Athlete>{
        return convertAthletes()
    }
}