package com.mosin.mvp_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mosin.mvp_kotlin.databinding.ItemRepoBinding
import com.mosin.mvp_kotlin.mvp.presenter.list.IUserRepoListPresenter
import com.mosin.mvp_kotlin.mvp.view.list.IUserReposItemView

class UserReposAdapter(val presenter: IUserRepoListPresenter) :
    RecyclerView.Adapter<UserReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposAdapter.ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: UserReposAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IUserReposItemView {

        override var pos = -1

        override fun setNameRepos(nameRepo: String) = with(vb) {
            vb.txtRepoName.text = nameRepo
        }
    }
}
