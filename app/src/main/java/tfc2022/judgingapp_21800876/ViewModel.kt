package tfc2022.judgingapp_21800876

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.data.AthleteDatabase
import tfc2022.judgingapp_21800876.data.AthleteRoom
import tfc2022.judgingapp_21800876.data.Model

class ViewModel(application: Application) : AndroidViewModel(application){

    private val model = Model(
        AthleteDatabase.getInstance(application).athleteDao()
    )

    fun getRaleys(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getRaleys(onFinished)
        }
    }

    fun getTantrums(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getTantrums(onFinished)
        }
    }

    fun getRolls(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getRolls(onFinished)
        }
    }

    fun getHistory(onFinished: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getHistory(onFinished)
        }
    }

    fun addHistory(trick : String){
        model.addHistory(trick)
    }

    fun addListOfTricks(trick : String){
        model.addListOfTricks(trick)
    }

    fun getAthleteListOfTricks(): String {
        return model.athleteListOfTricks
    }

    fun getAthletes(callback: (List<Athlete>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getAthletes(callback)
        }
    }

    fun addAthlete(athlete : Athlete){
        model.addAthlete(athlete)
    }

    fun updateTricks(tricks : String, name : String){
        model.updateTricks(tricks, name)
    }

    fun updateExecution(execution : String, name : String){
        model.updateExecution(execution, name)
    }

    fun updateIntensity(intensity : String, name : String){
        model.updateIntensity(intensity, name)
    }

    fun updateComprehension(comprehension : String, name : String){
        model.updateComprehension(comprehension, name)
    }

    fun updateScore(score : Double, name : String){
        model.updateScore(score, name)
    }

    fun getAllAthletesList(): List<Athlete> {
        return model.getAllAthletesList()
    }
}