package com.ticket.ui.records

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ticket.R
import com.ticket.ui.menu.MenuActivity
import com.ticket.utils.adapter.Adapter
import com.ticket.utils.setGone
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.activity_records.*

class RecordsActivity : AppCompatActivity() {

    private lateinit var viewModel: RecordsViewModel
    private lateinit var adapter: Adapter

    companion object {
        fun newIntent(context: Context) = Intent(context, RecordsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        viewModel = ViewModelProviders.of(this).get(RecordsViewModel::class.java)
        setObservers()
        btn_back.setOnClickListener{
            startActivity(MenuActivity.newIntent(this))
        }
    }
    private fun setObservers() {
        setUsersListObserver()
        initRecycles()
        observeSuccessMessage()
        observeUnSuccessMessage()
        setLoadingObserver()
    }

    private fun initRecycles() {
        adapter = Adapter(arrayListOf())
        user_list?.adapter = adapter
        user_list?.layoutManager = LinearLayoutManager(this)
    }

    private fun setUsersListObserver() {
        viewModel.users.observe(this, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })
    }

    private fun observeSuccessMessage(){
        viewModel.successLiveData.observe(this, androidx.lifecycle.Observer{
            Snackbar.make(user_list,getString(R.string.message_success), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun observeUnSuccessMessage(){
        viewModel.errorLiveData.observe(this, androidx.lifecycle.Observer{
            Snackbar.make(user_list,getString(R.string.message_error), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun setLoadingObserver(){
        viewModel.isLoading.observe(this, Observer {
            it?.let{
                if (it){
                    pb_records.setVisible()
                } else {
                    pb_records.setGone()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUsers()
    }
}
