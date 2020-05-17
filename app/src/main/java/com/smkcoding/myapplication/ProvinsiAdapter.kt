package com.smkcoding.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smkcoding.myapplication.covid.ProvinsiItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.provinsi_item.*

class ProvinsiAdapter(private val context: Context, private val items:
List<ProvinsiItem>, private val listener: (ProvinsiItem) -> Unit) :
    RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder (context, LayoutInflater.from(context).inflate(R.layout.provinsi_item, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: ProvinsiItem, listener: (ProvinsiItem) -> Unit) {
            txtProv?.text = item.attributes.provinsi
            txtPositive?.text = item.attributes.kasusPosi.toString()
            txtNegative?.text = item.attributes.kasusSemb.toString()
            txtDied?.text = item.attributes.kasusMeni.toString()

            containerView.setOnClickListener { listener(item) }
        }

    }

}