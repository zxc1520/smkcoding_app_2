package com.smkcoding.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.relawan_item.*

class RelawanAdapter (private val context: Context, private val items: ArrayList<RelawanModel>)
    : RecyclerView.Adapter<RelawanAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: RelawanModel) {
            txtName.text = item.nama
            txtEmail.text = item.email
            txtTelp.text = item.telp
            txtAlamat.text = item.alamat
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.relawan_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }
}