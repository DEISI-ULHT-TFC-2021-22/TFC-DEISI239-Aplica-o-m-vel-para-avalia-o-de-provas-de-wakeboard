package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.databinding.ItemTrickBinding

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
        holder.binding.trickName.text = items[position]
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }
}
