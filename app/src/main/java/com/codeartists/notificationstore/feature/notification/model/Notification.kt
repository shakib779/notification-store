package com.codeartists.notificationstore.feature.notification.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "packageName") var packageName: String = "Unknown",
    @ColumnInfo(name = "title") var title: String = "Unknown",
    @ColumnInfo(name = "body") var body: String = "No body"
)