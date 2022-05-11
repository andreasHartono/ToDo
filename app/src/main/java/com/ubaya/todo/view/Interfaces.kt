package com.ubaya.todo.view

import android.widget.CompoundButton
import com.ubaya.todo.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,isChecked:Boolean, obj:Todo)
}