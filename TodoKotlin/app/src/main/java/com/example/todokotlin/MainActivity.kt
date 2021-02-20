package com.example.todokotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todojava.Todo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mSaveButton: Button
    private lateinit var mResultRecyclerView: RecyclerView
    private lateinit var mTodoEditText: EditText
    private lateinit var db: AppDatabase
    private lateinit var mAdapter: TodoRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSaveButton = findViewById(R.id.btn_save)
        mResultRecyclerView = findViewById(R.id.recycler_view)
        mTodoEditText = findViewById(R.id.et_todo)

//        db = Room.databaseBuilder(this, ,"todo_db")
        db = Room.databaseBuilder(this, AppDatabase::class.java, "todo-db")
            .allowMainThreadQueries()
            .build()

        mLayoutManager = LinearLayoutManager(this)
        mAdapter = TodoRecyclerViewAdapter().apply {
            mTodoList = db.todoDao().select()
        }
        mResultRecyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        mSaveButton.setOnClickListener { v ->

            val todo: Todo = Todo().apply {
                content = mTodoEditText.text.toString()
            }
            db.todoDao().insert(todo)
            mAdapter.updateList(db.todoDao().select())
            mTodoEditText.text = null
        }
    }
    private fun getStr():String{
        //return db?.todoDao()?.select().toString()
        return db.todoDao().select().map{it.content}.joinToString(separator =  "\n"){ it!! }
        //    ?.stream()?.map { Todo::content }.collect(Collectors.joining("\n"))



    }
}