package com.ticket.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.ticket.R
import com.ticket.di.base.BaseActivity
import com.ticket.di.network.PostListViewModel
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginActivity : BaseActivity() {
    private lateinit var viewModel: PostListViewModel

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)

        setPostListObserver()

        btn_login.setOnClickListener {
            viewModel.loadPosts()
        }
    }


    private fun setPostListObserver() {
        viewModel.posts.observe(this, androidx.lifecycle.Observer{
            Snackbar.make(btn_login,"Успешно!", Snackbar.LENGTH_LONG).show()
        })
    }
}
