package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.R
import tfc2022.judgingapp_21800876.databinding.ItemTrickBinding

/* Adapter for the History Trick list
*
* This class objective is to populate a list with every selected trick, with icons for height and
* wave.
*
*/

class AdapterHistoryList (
    private var items: List<String> = listOf())
    : RecyclerView.Adapter<AdapterHistoryList.HistoryListViewHolder>() {
    class HistoryListViewHolder(val  binding: ItemTrickBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        return HistoryListViewHolder(
            ItemTrickBinding.inflate (
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        val string = items[position]
        val parts = string.split(",")

        holder.binding.trickName.text = parts[0]
        holder.binding.trickHeight.setImageResource(getIcon(parts[1]))
        holder.binding.trickWave.setImageResource(getIcon(parts[2]))
        holder.binding.trickStats.text = parts[3]
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    private fun getIcon(iconName : String): Int {
        when(iconName){
            "High" -> return R.drawable.ic_baseline_keyboard_double_arrow_up_24
            "Medium" -> return R.drawable.ic_baseline_keyboard_arrow_up_24
            "Down" -> return R.drawable.ic_baseline_keyboard_arrow_down_24
            "Left" -> return R.drawable.waves_arrow_left
            "Top" -> return R.drawable.waves_arrow_up
            "Right" -> return R.drawable.waves_arrow_right
        }
        return R.drawable.ic_baseline_close_24
    }
}
