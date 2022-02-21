package ru.dillab.sportdiary.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.dillab.sportdiary.databinding.ResultsItemBinding
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.formatLongToDateString


class ResultsAdapter : ListAdapter<DayResult, ResultsAdapter.ResultsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsViewHolder {
        return ResultsViewHolder(
            ResultsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultsAdapter.ResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ResultsViewHolder(private var binding: ResultsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: DayResult) {
            binding.apply {
                morningTime.text = result.morningTime.formatLongToDateString()
                eveningTime.text = result.eveningTime.formatLongToDateString()
                sleepHours.text = result.sleepHours
                pulse.text = result.pulse
                musclePain.text = result.musclePain
                productivity.text = result.productivity
                goals.text = result.goals
                qualities.text = result.qualities
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DayResult>() {
            override fun areItemsTheSame(oldItem: DayResult, newItem: DayResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DayResult, newItem: DayResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}



