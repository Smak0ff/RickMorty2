package com.example.rickmorty2.ui.pagedList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty2.R
import com.example.rickmorty2.databinding.RecyclerCharacterItemBinding
import com.example.rickmorty2.model.Results

class CharactersAdapter :
    PagedListAdapter<Results, CharactersAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        var character = getItem(position)
        if (character != null) {
            holder.bind(character)
        }
    }

    class CharacterViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private var mBinding = RecyclerCharacterItemBinding.bind(view)
        private var mImageView = mBinding.imageForRecyclerId
        private var mTextView = mBinding.nameForRecyclerId
        private var character: Results? = null


        fun bind(character: Results) {
            this.character = character
            mTextView.text = character.name
            Glide.with(mImageView.context)
                .load(character.image)
                .into(mImageView)
        }
    }

    //Когда-нибудь я с этим разберусь, но не сегодня.
    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
                newItem == oldItem
        }
    }
}