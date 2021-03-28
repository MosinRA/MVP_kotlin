package com.mosin.mvp_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosin.mvp_kotlin.databinding.FragmentUserInfoBinding
import com.mosin.mvp_kotlin.mvp.model.api.ApiHolder
import com.mosin.mvp_kotlin.mvp.model.entity.GitHubUser
import com.mosin.mvp_kotlin.mvp.model.entity.room.db.Database
import com.mosin.mvp_kotlin.mvp.model.image.IImageLoader
import com.mosin.mvp_kotlin.mvp.model.repo.CacheRepo
import com.mosin.mvp_kotlin.mvp.model.repo.ICacheRepo
import com.mosin.mvp_kotlin.mvp.model.repo.RetrofitGithubRepos
import com.mosin.mvp_kotlin.mvp.presenter.UserInfoPresenter
import com.mosin.mvp_kotlin.mvp.view.UserInfoView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import com.mosin.mvp_kotlin.ui.adapter.UserReposAdapter
import com.mosin.mvp_kotlin.ui.image.GlideImageLoader
import com.mosin.mvp_kotlin.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment() : MvpAppCompatFragment(),
    UserInfoView, IBackClickListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GitHubUser) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var ui: FragmentUserInfoBinding? = null
    private var adapter: UserReposAdapter? = null

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GitHubUser>(USER_ARG) as GitHubUser
        UserInfoPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            user,
            RetrofitGithubRepos(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                CacheRepo(Database.getInstance())
            )
        )
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

    override fun init() {
        ui?.rvUserRepo?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserReposAdapter(presenter.userReposListPresenter)
        ui?.rvUserRepo?.adapter = adapter
    }

    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        ui?.userLogin?.text = text
    }

//    override fun setImage(url: String) {
//        imageLoader.load(url, ui!!.ivAvatar)
//    }

    override fun showRepoInfo(scoreFork: Int, scoreViews: Int, language: String) {
        Toast.makeText(
            context,
            "Количество Форков $scoreFork, \nКоличество просмотров $scoreViews\nЯзык програмирования $language",
            Toast.LENGTH_SHORT
        ).show()
    }
}
