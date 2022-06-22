package tfc2022.judgingapp_21800876

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.data.AthleteDatabase
import tfc2022.judgingapp_21800876.data.AthleteModel

class AthleteViewModel(application: Application) : AndroidViewModel(application){

    private val model = AthleteModel(
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

    fun getAthletes(callback: (List<Athlete>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            model.getAthletes(callback)
        }
    }

    fun addAthlete(athlete : Athlete){
        model.addAthlete(athlete)
    }
}