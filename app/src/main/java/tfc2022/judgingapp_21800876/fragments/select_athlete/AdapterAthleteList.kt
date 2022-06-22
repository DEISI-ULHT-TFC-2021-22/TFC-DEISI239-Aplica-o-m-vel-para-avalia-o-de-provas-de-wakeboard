package tfc2022.judgingapp_21800876.fragments.select_athlete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tfc2022.judgingapp_21800876.data.Athlete
import tfc2022.judgingapp_21800876.databinding.ItemAthleteBinding

class AdapterAthleteList (
    private var items: List<Athlete> = listOf(),
    private val onClick: (Athlete) -> Unit)
    : RecyclerView.Adapter<AdapterAthleteList.AthleteListViewHolder>() {
    class AthleteListViewHolder(val  binding: ItemAthleteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthleteListViewHolder {
        return AthleteListViewHolder(
            ItemAthleteBinding.inflate (
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AthleteListViewHolder, position: Int) {
        holder.binding.athleteName.text = items[position].name

        holder.binding.athlete.setOnClickListener{ onClick(items[position]) }
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Athlete>) {
        this.items = items
        notifyDataSetChanged()
    }
}
