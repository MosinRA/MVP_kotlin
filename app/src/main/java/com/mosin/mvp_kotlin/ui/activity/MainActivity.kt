package com.mosin.mvp_kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mosin.mvp_kotlin.databinding.ActivityMainBinding
import com.mosin.mvp_kotlin.mvp.presenter.MainPresenter
import com.mosin.mvp_kotlin.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var ui: ActivityMainBinding? = null

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)

        ui?.btnCounter1?.setOnClickListener { presenter.counterClickOne() }
        ui?.btnCounter2?.setOnClickListener { presenter.counterClickTwo() }
        ui?.btnCounter3?.setOnClickListener { presenter.counterClickThree() }
    }

    override fun setBtnTextOne(text: String) {
        ui?.btnCounter1?.text = text
    }

    override fun setBtnTextTwo(text: String) {
        ui?.btnCounter2?.text = text
    }

    override fun setBtnTextThree(text: String) {
        ui?.btnCounter3?.text = text
    }
}
