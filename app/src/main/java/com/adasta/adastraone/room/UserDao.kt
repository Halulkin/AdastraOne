package com.adasta.adastraone.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>

//    @Query(
//        "SELECT * FROM user WHERE name LIKE :first AND " +
//                "status LIKE :last LIMIT 1"
//    )
//    fun findByName(name: String, status: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    fun delete(user: User)
}