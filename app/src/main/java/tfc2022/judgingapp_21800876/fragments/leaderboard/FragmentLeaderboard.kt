package tfc2022.judgingapp_21800876.fragments.leaderboard

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
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.Athlete
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
        binding.leaderboardList.layoutManager = LinearLayoutManager(requireContext())
        binding.leaderboardList.adapter = adapterLeaderboard
        viewModel.getAthletes{ updateListLeaderboard(it) }
    }

    private fun updateListLeaderboard(athleteList : List<Athlete>){
        CoroutineScope(Dispatchers.Main).launch {
            adapterLeaderboard.updateItems(athleteList)
        }
    }
}