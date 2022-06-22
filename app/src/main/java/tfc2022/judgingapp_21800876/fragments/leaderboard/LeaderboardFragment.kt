package tfc2022.judgingapp_21800876.fragments.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.databinding.FragmentLeaderboardBinding

private lateinit var binding : FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Fragment Title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.title_leaderboard)

        //Layout
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        //Binding
        binding = FragmentLeaderboardBinding.bind(view)
        return binding.root
    }
}