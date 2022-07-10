package tfc2022.judgingapp_21800876.fragments.leaderboard

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.databinding.ItemLeaderboardBinding

private lateinit var model : ViewModel

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

        holder.binding.athleteName.text = stringName
        holder.binding.athleteTricks.text = stringTricks.dropLast(2)

        holder.binding.saveScore.setOnClickListener { calculateScore(items[position], holder) }

        holder.binding.finalScore.text = "Final Score: " + items[position].score.toString()
    }

    override fun getItemCount() = items.size

    private fun calculateScore(athlete: Athlete, holder: LeaderboardListViewHolder){

        model.updateScore((
                holder.binding.executionInput.text.toString().toDouble() +
                holder.binding.intensityInput.text.toString().toDouble() +
                holder.binding.intensityInput.text.toString().toDouble()) * 3.33, athlete.name)

        Log.d("Score = ", athlete.score.toString())

        holder.binding.finalScore.text = "Final Score: " + athlete.score.toString()
    }

    fun updateItems(items: List<Athlete>, viewModel : ViewModel) {
        this.items = items
        model = viewModel
        notifyDataSetChanged()
    }
}