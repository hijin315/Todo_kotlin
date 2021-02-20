package com.example.todokotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todojava.Todo
@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun select(): List<Todo>

    @Query("INSERT INTO todo (content) VALUES(:content)")
    fun insert(content:String)

    @Insert
    fun insert(todo:Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    fun delete(id:Int)

    @Delete
    fun delete(todo: Todo)

    @Query("UPDATE todo SET completed = 1 WHERE id = :id")
    fun completed(id:Int)
}