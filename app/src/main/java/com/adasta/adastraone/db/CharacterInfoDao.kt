package com.adasta.adastraone.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


//@Dao
//interface CharacterInfoDao {
//    @Query("SELECT * FROM user")
//    suspend fun getAll(): List<CharacterInfo.Results>
//
////    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
////    fun loadAllByIds(userIds: IntArray): List<User>
//
////    @Query(
////        "SELECT * FROM user WHERE name LIKE :first AND " +
////                "status LIKE :last LIMIT 1"
////    )
////    fun findByName(name: String, status: String): User
//
//    @Insert
//    fun insertAll(vararg characters: CharacterInfo.Results)
//
//    @Insert
//    suspend fun insertUser(character: CharacterInfo.Results)
//
//    @Delete
//    fun delete(character: CharacterInfo.Results)
//}