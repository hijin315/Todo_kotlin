package com.example.todokotlin

class RecyclerViewItem {
    private lateinit var todolist: String

    fun setSubTitle(list: String) {
        todolist = list; }
    fun getIconDrawable():String
    { return todolist; }

}
