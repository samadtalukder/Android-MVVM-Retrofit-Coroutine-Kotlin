package com.samad_talukder.androidmvvmretrofitroomdbinkotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.R
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.model.RandomUserResponse
import com.samad_talukder.androidmvvmretrofitroomdbinkotlin.model.Result
import kotlinx.android.synthetic.main.item_random_user.view.*

class RandomUserAdapter : RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        return RandomUserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_random_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {
        val userList = differ.currentList[position]
        holder.dataBind(userList)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    inner class RandomUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun dataBind(results: Result) {
            itemView.tvTitle.text = results.name.first + results.name.last
            itemView.tvDescription.text = results.location.city
            Glide.with(itemView.context).load(results.picture.large).into(itemView.ivArticleImage)


            itemView.setOnClickListener {
                onItemClickListener?.let { it(results) }
            }
        }




    }
}