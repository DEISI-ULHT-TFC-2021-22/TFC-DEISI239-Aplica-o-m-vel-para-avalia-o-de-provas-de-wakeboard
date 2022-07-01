package tfc2022.judgingapp_21800876.fragments.leaderboard

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.databinding.ItemLeaderboardBinding

class AdapterLeaderboardList (
    private var items: List<Athlete> = listOf())
    : RecyclerView.Adapter<AdapterLeaderboardList.LeaderboardListViewHolder>() {
        class LeaderboardListViewHolder(val  binding: ItemLeaderboardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardListViewHolder {
        return LeaderboardListViewHolder(
            ItemLeaderboardBinding.inflate (
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LeaderboardListViewHolder, position: Int) {
        val stringName = "Athlete: " + items[position].name
        val stringTricks = "Tricks Performed: " + items[position].tricks
        var stringFinalScore = ""

        holder.binding.athleteName.text = stringName
        Log.d("Coisa", stringTricks.dropLast(2))
        holder.binding.athleteTricks.text = stringTricks

        val stringScore = "Execution: " + items[position].execution + " Intensity: " +
                items[position].intensity + " Comprehension: " + items[position].comprehension

        stringFinalScore = if(!items[position].fall) {
            "Final Score is " + items[position].score
        }else{
            "Fall"
        }

        holder.binding.athleteScore.text = stringScore
        holder.binding.athleteFinalScore.text = stringFinalScore
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Athlete>) {
        this.items = items
        notifyDataSetChanged()
    }
}