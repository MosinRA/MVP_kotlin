package com.mosin.mvp_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mosin.mvp_kotlin.databinding.FragmentUserInfoBinding
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.presenter.UserInfoPresenter
import com.mosin.mvp_kotlin.mvp.view.UserInfoView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, IBackClickListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GitHubUser) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var ui: FragmentUserInfoBinding? = null

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GitHubUser>(USER_ARG) as GitHubUser
        UserInfoPresenter(App.instance.router, user)
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

    override fun setLogin(text: String) {
        ui?.userLogin?.text = text
    }
}