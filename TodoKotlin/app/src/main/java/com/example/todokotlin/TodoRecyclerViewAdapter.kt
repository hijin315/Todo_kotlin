package com.example.todokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todojava.Todo

class TodoRecyclerViewAdapter :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>() {
    lateinit var mTodoList: List<Todo>

    fun updateList(list:List<Todo>){
        mTodoList = list
        notifyDataSetChanged()
    }

    //alt+insert -> implement Method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       holder.checkBox.text = mTodoList[position].content
    }

    class TodoViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {
        var checkBox : CheckBox
        init {
            checkBox = rootView.findViewById(R.id.cb_complete)
        }
   }
}