package tfc2022.judgingapp_21800876.fragments.leaderboard

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
        holder.binding.athleteName.text = items[position].name
        holder.binding.athleteTricks.text = items[position].tricks
        holder.binding.athleteScore.text = items[position].score.toString()
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Athlete>) {
        this.items = items
        notifyDataSetChanged()
    }
}