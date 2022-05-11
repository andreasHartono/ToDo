package com.ubaya.todo.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.todo.R
import com.ubaya.todo.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment() {
    private lateinit var viewModel:ListTodoViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf(),
        {item -> viewModel.cleartask(item)})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        recViewToDo.layoutManager = LinearLayoutManager(context)
        recViewToDo.adapter = todoListAdapter

        fabAddTodo.setOnClickListener {
            val action = TodoListFragmentDirections.todoListCreate()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner) {
            todoListAdapter.updateTodoList(it)
            txtError.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}