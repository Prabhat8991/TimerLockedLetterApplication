package com.example.letterapplication.letterlist.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.letterapplication.databinding.LetterItemLayoutBinding
import com.example.letterapplication.model.LetterUIModel

class LettersListAdapter(private val lettersList: List<LetterUIModel>, val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<LettersListAdapter.LettersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LettersViewHolder {
        return LettersViewHolder.from(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: LettersViewHolder, position: Int) {
        holder.bind(lettersList[position])
    }

    override fun getItemCount(): Int = lettersList.size

    class LettersViewHolder(private val binding: LetterItemLayoutBinding, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(letterUIModel: LetterUIModel) {
            binding.letterInfo = letterUIModel
            if(!letterUIModel.lockStatus) {
                binding.onItemClickListener = onItemClickListener
            } else {
                binding.itemConstraintLayout.isClickable = false
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, onItemClickListener: OnItemClickListener): LettersViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LetterItemLayoutBinding.inflate(inflater, parent, false)
                return LettersViewHolder(binding, onItemClickListener)
            }
        }
    }
}
//Data binding in item layout directly
class OnItemClickListener(val clickListener: (title: String, description: String) -> Unit) {
    fun onClick(title: String, description: String) = clickListener(title, description)
}
