package com.ubaya.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todo.R
import com.ubaya.todo.databinding.FragmentEditTodoBinding
import com.ubaya.todo.databinding.TodoItemLayoutBinding
import com.ubaya.todo.model.Todo
import com.ubaya.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), RadioClickListener, SaveChangesListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    //private lateinit var dataBinding:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(
                inflater,R.layout.fragment_edit_todo,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()
        dataBinding.radiolistener = this
        dataBinding.listener = this
//        textJudul.text = "Edit Todo"
//        buttonAdd.text = "Save Changes"

//        buttonAdd.setOnClickListener {
//            val radio = view?.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(
//                uuid,
//                txtTitle.text.toString(),
//                txtNotes.text.toString(),
//                radio.tag.toString().toInt()
//            )
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            //d
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            when(it.priority) {
//                1 -> radioLow.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onSaveChanges(v: View, obj: Todo) {
        viewModel.update(obj.uuid, obj.title, obj.notes, obj.priority)
        Toast.makeText(v.context,"Todo Updated",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}