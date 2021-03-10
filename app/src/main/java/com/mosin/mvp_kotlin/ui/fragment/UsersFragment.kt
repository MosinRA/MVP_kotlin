package com.mosin.mvp_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosin.mvp_kotlin.databinding.FragmentUsersBinding
import com.mosin.mvp_kotlin.mvp.model.GitHubUsersRepo
import com.mosin.mvp_kotlin.mvp.presenter.UsersPresenter
import com.mosin.mvp_kotlin.mvp.view.UsersView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import com.mosin.mvp_kotlin.ui.adapter.UsersRVAdapter
import com.mosin.mvp_kotlin.ui.navigation.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, IBackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(GitHubUsersRepo(), App.instance.router, AndroidScreens())
    }

    private var ui: FragmentUsersBinding? = null
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        ui = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun init() {
        ui?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        ui?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}