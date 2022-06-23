package tfc2022.judgingapp_21800876.fragments.judge_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.databinding.ItemTrickBinding

class AdapterRollList(
    private var items: List<String> = listOf(),
    private val onClick: (String) -> Unit)
    : RecyclerView.Adapter<AdapterRollList.RollListViewHolder>() {
    class RollListViewHolder(val  binding: ItemTrickBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RollListViewHolder {
        return RollListViewHolder(
            ItemTrickBinding.inflate (
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RollListViewHolder, position: Int) {
        holder.binding.trickName.text = items[position]

        holder.binding.trick.setOnClickListener{ onClick(items[position]) }
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }
}
