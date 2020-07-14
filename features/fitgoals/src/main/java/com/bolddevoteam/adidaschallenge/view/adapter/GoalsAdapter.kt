package com.bolddevoteam.adidaschallenge.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bolddevoteam.adidaschallenge.core.extensions.inflate
import com.bolddevoteam.adidaschallenge.fitgoals.R
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalItem
import kotlinx.android.synthetic.main.item_goal_list.view.*

class GoalsAdapter constructor(private val itemClick: (GoalItem) -> Unit,
                               private val signedIn: Boolean) :
    ListAdapter<GoalItem, GoalsAdapter.ViewHolder>( GoalsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), position)

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_goal_list)) {

        fun bind(item: GoalItem, position: Int) {
            itemView.apply {
                tvIncitement.text = resources.getString(R.string.incitement_sign_in)
                if(signedIn) tvIncitement.text = resources.getString(R.string.incitement_detail)
                tvTitle.text = item.title
                tvDescription.text = item.description
                tvGoal.text = "${item.goal} steps"
                pbStepsProgress.progress = testProgressBar(item.goal.toInt())
            }
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }

    fun testProgressBar(value: Int) : Int {
        if(value > 5000) return 50
        if(value > 1000) return 20
        if(value > 500) return 10
        return 5
    }
}

private class GoalsCallback : DiffUtil.ItemCallback<GoalItem>() {
    override fun areItemsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GoalItem, newItem: GoalItem): Boolean =
        oldItem == newItem
}
