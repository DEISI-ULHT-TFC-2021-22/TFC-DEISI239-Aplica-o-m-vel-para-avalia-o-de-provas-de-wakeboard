package tfc2022.judgingapp_21800876.fragments.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentLeaderboardBinding

private lateinit var binding : FragmentLeaderboardBinding
private lateinit var viewModel : ViewModel

class LeaderboardFragment : Fragment() {
    private val adapterLeaderboard = AdapterLeaderboardList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_leaderboard)

        //Layout
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        //Binding
        binding = FragmentLeaderboardBinding.bind(view)

        //ViewModel
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        categorySpinnerSetup()
        binding.leaderboardList.layoutManager = LinearLayoutManager(requireContext())
        binding.leaderboardList.adapter = adapterLeaderboard
        viewModel.getAthletes{ updateListLeaderboard(it) }
    }

    private fun updateListLeaderboard(athleteList : List<Athlete>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterLeaderboard.updateItems(athleteList)
        }
    }

    private fun categorySpinnerSetup(){
        val spinner: Spinner = binding.spinnerCategory

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                updateListLeaderboard(getAthleteByCategory(spinner.selectedItem.toString()))
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        }

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.category_array_plus_all,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun getAthleteByCategory(category:String): MutableList<Athlete> {
        if(category == "All Categories") {
            return viewModel.getAllAthletesList() as MutableList<Athlete>
        }

        val allAthletes = viewModel.getAllAthletesList()
        val chosenAthletes = mutableListOf<Athlete>()
        for(athlete in allAthletes) {
            if(athlete.category == category) {
                chosenAthletes.add(athlete)
            }
        }

        return chosenAthletes
    }
}