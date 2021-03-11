package com.adasta.adastraone.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "status") var status: String?
)


