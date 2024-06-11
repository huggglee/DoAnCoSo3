package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dacs3.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE user_id = :id")
    fun getUserById(id: Int): Flow<User>

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE userName = :username AND password = :password")
    fun getUser(username: String, password: String): User


//    @Transaction
//    @Query("SELECT * FROM User WHERE userId = :id")
//    fun getUserWithPlaylists(id: Int): UserWithPlaylists
}
