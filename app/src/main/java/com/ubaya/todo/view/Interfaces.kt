package com.ubaya.todo.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todo.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,isChecked:Boolean, obj:Todo)
}

interface TodoEditClick {
    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v:View,priority:Int,obj: Todo)
}

interface SaveChangesListener {
    fun onSaveChanges(v:View, obj:Todo)
}