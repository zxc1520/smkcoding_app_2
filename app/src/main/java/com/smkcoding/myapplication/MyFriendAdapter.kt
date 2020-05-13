package com.smkcoding.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smkcoding.myapplication.covid.Attributes
import com.smkcoding.myapplication.covid.CovidDataItemItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.myfriend_item.*

class MyFriendAdapter(private val context: Context, private val items: List<Attributes>, private val listener:(Attributes) -> Unit) :
    RecyclerView.Adapter<MyFriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.myfriend_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: Attributes, listener: (Attributes) -> Unit) {
            txtCountries.text = item.countryRegion
            txtConfirmed.text = item.confirmed.toString()
            txtDeaths.text = item.deaths.toString()
            txtRecovered.text = item.recovered.toString()

            containerView.setOnClickListener{ listener(item) }
        }

    }

}