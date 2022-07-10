package tfc2022.judgingapp_21800876.fragments.select_athlete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.NavigationManager
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.databinding.FragmentSelectAthleteBinding

private lateinit var binding : FragmentSelectAthleteBinding
private lateinit var viewModel : ViewModel

class SelectAthleteFragment : Fragment() {
    private val adapterAthlete = AdapterAthleteList(onClick = ::onItemClick)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_select_athlete)

        //Layout
        val view = inflater.inflate(R.layout.fragment_select_athlete, container, false)

        //Binding
        binding = FragmentSelectAthleteBinding.bind(view)

        //ViewModel
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.athleteList.layoutManager = LinearLayoutManager(requireContext())
        binding.athleteList.adapter = adapterAthlete
        viewModel.getAthletes{ updateListAthlete(it) }
    }

    private fun onItemClick(athlete: Athlete) {
        NavigationManager.goToJudgeSheetFragment(parentFragmentManager, athlete)
    }

    private fun updateListAthlete(athleteList : List<Athlete>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterAthlete.updateItems(athleteList)
        }
    }
}