package tfc2022.judgingapp_21800876

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.data.athlete.AthleteDatabase

class ViewModel(application: Application) : AndroidViewModel(application){

    private val model = Model(
        AthleteDatabase.getInstance(application).athleteDao()
    )

    fun getHistory(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getHistory(onFinished)
        }
    }

    fun addHistory(trick : String){
        model.addHistory(trick)
    }

    fun addListOfTricks(trick: String, trickHeight: String, trickWave: String, stats : String){

        model.addListOfTricks(trick, trickHeight, trickWave, stats)
    }

    fun getAthleteListOfTricks(): String {
        return model.athleteListOfTricks
    }

    fun updateTricks(tricks : String, name : String){
        model.updateTricks(tricks, name)
    }

    fun addListOfGrabs(grab: String){
        model.addListOfGrabs(grab)
    }

    fun getAthleteListOfGrabs(): String {
        return model.athleteListOfGrabs
    }

    fun updateGrabs(grabs : String, name : String){
        model.updateGrabs(grabs, name)
    }

    fun getAthletes(callback: (List<Athlete>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getAthletes(callback)
        }
    }

    fun addAthlete(athlete : Athlete){
        model.addAthlete(athlete)
    }

    fun updateScore(score : Double, name : String){
        model.updateScore(score, name)
    }

    fun updateStats(execution : String, intensity : String, composition : String, name : String){
        model.updateStats(execution, intensity, composition, name)
    }

    fun getAllAthletesList(): List<Athlete> {
        return model.getAllAthletesList()
    }
}