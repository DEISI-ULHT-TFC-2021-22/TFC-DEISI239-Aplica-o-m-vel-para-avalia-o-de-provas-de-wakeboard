package tfc2022.judgingapp_21800876.fragments.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tfc2022.judgingapp_21800876.ViewModel
import tfc2022.judgingapp_21800876.data.athlete.Athlete
import tfc2022.judgingapp_21800876.databinding.ItemLeaderboardBinding
import kotlin.math.round

/* Adapter for the Leaderboard
*
* This class objective is to populate a list with athletes and show all the information needed
* so judges can have and overview for evaluation.
*
*/


private lateinit var viewModel : ViewModel

class AdapterLeaderboardList (
    private var items: List<Athlete> = listOf(),
    private val onCallBackCalculate: () -> Unit)
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
        val stringGrabs = "Grabs Performed: " + items[position].grabs.replace("\n", "")

        holder.binding.athleteName.text = stringName
        holder.binding.athleteTricks.text = stringTricks.dropLast(2)
        holder.binding.athleteGrabs.text = stringGrabs.dropLast(2)

        holder.binding.saveScore.setOnClickListener { calculateScore(items[position], holder) }

        holder.binding.athleteStats.text =
                "Execution: " + items[position].execution + "\n" +
                "Intensity: " + items[position].intensity + "\n" +
                "Composition: " + items[position].composition

        holder.binding.finalScore.text = "Final Score: " + items[position].score.toString()
    }

    override fun getItemCount() = items.size

    private fun calculateScore(athlete: Athlete, holder: LeaderboardListViewHolder){
        val score = round(holder.binding.executionInput.text.toString().toDouble() * 3.33 +
                    holder.binding.intensityInput.text.toString().toDouble() * 3.34 +
                    holder.binding.compositionInput.text.toString().toDouble() * 3.33)

        viewModel.updateScore(score, athlete.name)

        viewModel.updateStats(holder.binding.executionInput.text.toString(),
                              holder.binding.intensityInput.text.toString(),
                              holder.binding.compositionInput.text.toString(), athlete.name)

        holder.binding.athleteStats.text =
                    "Execution: " + holder.binding.executionInput.text.toString() + "\n" +
                    "Intensity: " + holder.binding.intensityInput.text.toString() + "\n" +
                    "Composition: " + holder.binding.compositionInput.text.toString()

        holder.binding.finalScore.text = "Final Score: $score"

        CoroutineScope(Dispatchers.IO).launch {
            delay(50)
            onCallBackCalculate.invoke()
        }

        clear(holder)
    }

    private fun clear(holder: LeaderboardListViewHolder){
        holder.binding.executionInput.text.clear()
        holder.binding.intensityInput.text.clear()
        holder.binding.compositionInput.text.clear()
    }

    fun updateItems(items: List<Athlete>, viewModel : ViewModel) {
        this.items = items
        tfc2022.judgingapp_21800876.fragments.leaderboard.viewModel = viewModel
        notifyDataSetChanged()
    }
}