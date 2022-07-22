package tfc2022.judgingapp_21800876

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.fragments.add_athlete.AddAthleteFragment
import tfc2022.judgingapp_21800876.fragments.judge_sheet.JudgeSheetFragment
import tfc2022.judgingapp_21800876.fragments.leaderboard.LeaderboardFragment
import tfc2022.judgingapp_21800876.fragments.select_athlete.SelectAthleteFragment

/* Navigation Manager Object
*
* An object that handles changing screens for the drawer menu.
*
*/

object NavigationManager {
    private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    //Criar as funções de navegação

    fun goToJudgeSheetFragment(fm: FragmentManager, athlete : Athlete) {
        placeFragment(fm, JudgeSheetFragment.newInstance(athlete))
    }

    fun goToAddAthleteFragment(fm: FragmentManager) {
        placeFragment(fm, AddAthleteFragment())
    }

    fun goToSelectAthleteFragment(fm: FragmentManager) {
        placeFragment(fm, SelectAthleteFragment())
    }

    fun goToLeaderboardFragment(fm: FragmentManager) {
        placeFragment(fm, LeaderboardFragment())
    }
}