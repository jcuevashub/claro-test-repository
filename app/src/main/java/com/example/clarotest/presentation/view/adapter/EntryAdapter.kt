package com.example.clarotest.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.clarotest.databinding.ItemRecylerBinding
import com.example.clarotest.domain.models.EntryDetails
import java.util.Locale

class EntryAdapter(private val listener: EntryItemListener?, private var entryList: ArrayList<EntryDetails>?
) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>(), Filterable {

    interface EntryItemListener {
        fun onClickedItem(entry: EntryDetails)
    }

    private var itemsFiltered: ArrayList<EntryDetails> = entryList!!

    class EntryViewHolder(
        private val binding: ItemRecylerBinding,
        private val listener: EntryItemListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var entries: EntryDetails

        fun bind(entries: EntryDetails) {
            this.entries = entries;
            binding.entries = this.entries
        }

        override fun onClick(v: View?) {
            entries.let { listener.onClickedItem(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding = ItemRecylerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntryViewHolder(binding, listener!!)
    }

    override fun getItemCount(): Int = entryList!!.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase(Locale.ROOT)
                val filteredList = if (query.isEmpty()) {
                    entryList
                } else {
                    entryList!!.filter {
                        it.API.lowercase(Locale.ROOT).contains(query) ||
                        it.Description.lowercase(Locale.ROOT).contains(query)
                    }
                }!!.toMutableList()

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsFiltered = results?.values as ArrayList<EntryDetails>
                if(itemsFiltered.isEmpty()) {
                    itemsFiltered = entryList!!
                } else {
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(itemsFiltered[position])

        holder.itemView.setOnClickListener {
            listener!!.onClickedItem(itemsFiltered[position])
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<EntryDetails>) {
        entryList!!.addAll(list)
        notifyDataSetChanged()
    }
}