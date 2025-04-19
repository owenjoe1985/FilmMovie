package com.example.filmmovie.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.filmmovie.R
import com.example.filmmovie.models.home.ResponseGenresList.ResponseGenresListItem
import com.example.filmmovie.databinding.ItemHomeGenreListBinding
import com.example.filmmovie.models.home.ResponseMoviesList
import javax.inject.Inject


class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>(){

    private lateinit var binding: ItemHomeGenreListBinding
    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.ViewHolder {
        binding = ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: GenresAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
        /*holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }*/
        /*if(selectedItemPosition == position)
            binding.cardView.setBackgroundColor(Color.parseColor("#DC746C"))
        else
            binding.cardView.setBackgroundColor(Color.parseColor("#E49B83"))*/
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n", "ResourceAsColor", "NotifyDataSetChanged")
        fun setData(item: ResponseGenresListItem){
            binding.apply {
                nameTxt.text = item.name
                //Click
                root.setOnClickListener {
                    selectedItemPosition = position
                    notifyDataSetChanged()
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                if(selectedItemPosition == position)
                    binding.cardView.setBackgroundColor(Color.parseColor("#feaa2e"))
                else
                    binding.cardView.setBackgroundColor(Color.parseColor("#B3B3B3"))
            }
        }
    }

    private var onItemClickListener: ((ResponseGenresListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseGenresListItem) -> Unit){
        onItemClickListener = listener
    }


    private val differCallback = object : DiffUtil.ItemCallback<ResponseGenresListItem>(){
        override fun areItemsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

}