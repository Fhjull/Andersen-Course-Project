package ru.dillab.sportdiary.ui.results

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.dillab.sportdiary.R
import ru.dillab.sportdiary.databinding.ResultsItemBinding
import ru.dillab.sportdiary.domain.model.DayResult
import ru.dillab.sportdiary.utils.formatLongToDateString


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
                if (result.morningTime != null) {
                    morningTime.visibility = VISIBLE
                    morningTime.text = itemView.context.getString(
                        R.string.item_morning_unit,
                        result.morningTime.formatLongToDateString()
                    )
                } else {
                    morningTime.visibility = GONE
                }
                if (result.sleepHours != null) {
                    sleepHours.visibility = VISIBLE
                    sleepHours.text = itemView.context.getString(
                        R.string.item_night_sleep_text,
                        result.sleepHours
                    )
                } else {
                    sleepHours.visibility = GONE
                }
                if (result.pulse != null) {
                    pulse.visibility = VISIBLE
                    pulse.text = itemView.context.getString(
                        R.string.item_pulse_text,
                        result.pulse
                    )
                } else {
                    pulse.visibility = GONE
                }
                if (result.musclePain != null) {
                    musclePain.visibility = VISIBLE
                    musclePain.text = itemView.context.getString(
                        R.string.item_muscle_pain_text,
                        result.musclePain
                    )
                } else {
                    musclePain.visibility = GONE
                }
                if (result.eveningTime != null) {
                    eveningTime.visibility = VISIBLE
                    eveningTime.text = itemView.context.getString(
                        R.string.item_evening_unit,
                        result.eveningTime.formatLongToDateString()
                    )
                } else {
                    eveningTime.visibility = GONE
                }
                if (result.productivity != null) {
                    productivity.visibility = VISIBLE
                    productivity.text =
                        itemView.context.getString(
                            R.string.item_productivity_text,
                            result.productivity
                        )
                } else {
                    productivity.visibility = GONE
                }
                if (result.goals != null) {
                    goals.visibility = VISIBLE
                    goals.text = itemView.context.getString(
                        R.string.item_goals_text,
                        result.goals
                    )
                } else {
                    goals.visibility = GONE
                }
                if (result.qualities != null) {
                    qualities.visibility = VISIBLE
                    qualities.text =
                        itemView.context.getString(
                            R.string.item_qualities_text,
                            result.qualities
                        )
                } else {
                    qualities.visibility = GONE
                }
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



