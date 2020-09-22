package com.codeartists.notificationstore.feature.notification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.codeartists.notificationstore.R
import com.codeartists.notificationstore.feature.notification.model.Notification
import com.codeartists.notificationstore.shared.AdapterOnClickListener

class NotificationListAdapter(private val notificationList: List<Notification>, private val hook: AdapterOnClickListener) :
    RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notification = notificationList[position]

        val id = notificationList[position].id
        val packageName = notificationList[position].packageName
        val title = "Sender : ${notificationList[position].title}"
        val body = "Text : ${notificationList[position].body}"
        holder.textViewTitle.text = title
        holder.textViewBody.text = body

        holder.cardViewNotificationItem.setOnLongClickListener {
            hook.onClickItem(position, hashMapOf("packageName" to packageName, "id" to id))
            true
        }
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewBody: TextView = itemView.findViewById(R.id.textViewBody)
        val cardViewNotificationItem: CardView = itemView.findViewById((R.id.cardViewNotificationItem))
        var notification: Notification? = null
    }
}