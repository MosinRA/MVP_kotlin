package com.mosin.mvp_kotlin.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mosin.mvp_kotlin.R
import com.mosin.mvp_kotlin.databinding.ActivityMainBinding
import com.mosin.mvp_kotlin.mvp.presenter.MainPresenter
import com.mosin.mvp_kotlin.mvp.view.MainView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import com.mosin.mvp_kotlin.ui.adapter.UsersRVAdapter
import com.mosin.mvp_kotlin.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)
    private var ui: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}
