package com.mosin.mvp_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mosin.mvp_kotlin.databinding.FragmentUserInfoBinding
import com.mosin.mvp_kotlin.mvp.model.GitHubUsersRepo
import com.mosin.mvp_kotlin.mvp.presenter.UserInfoPresenter
import com.mosin.mvp_kotlin.mvp.view.UserInfoView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, IBackClickListener {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    private var ui: FragmentUserInfoBinding? = null

    private val presenter by moxyPresenter {
        UserInfoPresenter(GitHubUsersRepo(), App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserInfoBinding.inflate(inflater, container, false).also {
            ui = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun backPressed(): Boolean = presenter.backClick()

    override fun initUserInfo(pos: Int) {

    }
}