package com.example.todokotlin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todojava.Todo

@Database(entities = [Todo::class], version = 1) //마지막에 version을 꼭 써줘야함

abstract class AppDatabase: RoomDatabase(){
    abstract fun todoDao() : TodoDao
}