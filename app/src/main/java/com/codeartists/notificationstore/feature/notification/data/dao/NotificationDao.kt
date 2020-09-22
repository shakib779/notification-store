package com.codeartists.notificationstore.feature.notification.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codeartists.notificationstore.feature.notification.model.Notification

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification")
    fun getAll(): List<Notification>

    @Query("SELECT * FROM Notification WHERE packageName = :packageName")
    fun findByPackageName(packageName: String): List<Notification>

    @Query("DELETE FROM Notification WHERE id IN (:idList)")
    fun deleteItems(idList: List<Int>)

    @Insert
    fun insertAll(vararg notifications: Notification)

    @Delete
    fun delete(notification: Notification)
}
