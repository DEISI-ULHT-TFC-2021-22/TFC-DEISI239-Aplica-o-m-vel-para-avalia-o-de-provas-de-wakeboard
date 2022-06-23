package tfc2022.judgingapp_21800876

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.data.AthleteDatabase
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

    fun getAthleteListOfTricks(): MutableList<String> {
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
}