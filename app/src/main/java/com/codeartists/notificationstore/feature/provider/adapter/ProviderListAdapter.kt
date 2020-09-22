package com.codeartists.notificationstore.feature.provider.adapter

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.codeartists.notificationstore.R
import com.codeartists.notificationstore.feature.provider.model.Provider
import com.codeartists.notificationstore.shared.AdapterOnClickListener

class ProviderListAdapter(private val providerList: List<Provider>, private val hook: AdapterOnClickListener) :
    RecyclerView.Adapter<ProviderListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.provider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.provider = providerList[position]

        val title = providerList[position].name
        val packageName = providerList[position].packageName
        holder.textViewTitle.text = title

        holder.cardViewProviderItem.setOnClickListener {
            hook.onClickItem(position, hashMapOf("name" to title, "packageName" to packageName))
        }
    }

    override fun getItemCount(): Int {
        return providerList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val cardViewProviderItem: CardView = itemView.findViewById((R.id.cardViewProviderItem))
        var provider: Provider? = null
    }
}