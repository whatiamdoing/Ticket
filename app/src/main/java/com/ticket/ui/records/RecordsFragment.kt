package com.ticket.ui.records

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ticket.R
import com.ticket.base.BaseActivity
import com.ticket.utils.adapter.Adapter
import com.ticket.utils.setGone
import com.ticket.utils.setVisible
import kotlinx.android.synthetic.main.fragment_records.*

class RecordsFragment : Fragment() {

    private lateinit var viewModel: RecordsViewModel
    private lateinit var adapter: Adapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProviders.of(activity!!).get(RecordsViewModel::class.java)
        initRecycles()
        setObservers()
        setOnClickListeners()
    }

    private fun setObservers() {
        setUsersListObserver()
        setLoadingObserver()
        observeUnSuccessMessage()
    }

    private fun setOnClickListeners() {
        btn_close?.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    private fun initRecycles() {
        adapter = Adapter(arrayListOf())
        user_list?.adapter = adapter
        user_list?.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun setUsersListObserver() {
        viewModel.users.observe(activity!!, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })
    }

    private fun observeUnSuccessMessage() {
        viewModel.errorLiveData.observe(activity!!, androidx.lifecycle.Observer {
            activity?.let {
                (it as BaseActivity).showMessage(getString(R.string.message_error))
            }
        })
    }

    private fun setLoadingObserver() {
        activity?.let { fragment ->
            viewModel.isLoading.observe(fragment, Observer {
                it?.let {
                    if (it){
                        pb_records.setVisible()
                    } else {
                        pb_records.setGone()
                    }
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUsers()
    }
}
