package com.mosin.mvp_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mosin.mvp_kotlin.databinding.FragmentUsersBinding
import com.mosin.mvp_kotlin.mvp.presenter.UsersPresenter
import com.mosin.mvp_kotlin.mvp.view.UsersView
import com.mosin.mvp_kotlin.ui.App
import com.mosin.mvp_kotlin.ui.IBackClickListener
import com.mosin.mvp_kotlin.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, IBackClickListener {
    private var ui: FragmentUsersBinding? = null
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var  hb: ImageView
    private val adapter by lazy {
        UsersRVAdapter(presenter.usersListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        ui = it
        onSwipeDelete()
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun init() {
        ui?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        ui?.rvUsers?.adapter = adapter
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(ui?.rvUsers)
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

    private fun onSwipeDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                presenter.usersListPresenter.users.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }).attachToRecyclerView(ui?.rvUsers)
    }
}


class ItemTouchHelperCallback(private val adapter: UsersRVAdapter) :
    ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
            dragFlags,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }
}
