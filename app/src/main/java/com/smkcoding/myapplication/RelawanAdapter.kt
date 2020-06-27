package com.smkcoding.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frame_relawan.*
import kotlinx.android.synthetic.main.relawan_item.*

class RelawanAdapter (private val context: Context, private val items: ArrayList<RelawanModel>)
    : RecyclerView.Adapter<RelawanAdapter.ViewHolder>() {

    lateinit var ref : DatabaseReference
    lateinit var auth : FirebaseAuth

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
        holder.bindItem(items[position])

        val nama : String? = items.get(position).nama
        val email : String? = items.get(position).email
        val telp : String? = items.get(position).telp
        val alamat : String? = items.get(position).alamat

        holder.txtName.setText("Nama $nama")
        holder.txtEmail.setText("Email $email")
        holder.txtTelp.setText("Telp $telp")
        holder.txtAlamat.setText("Alamat $alamat")

        holder.rv_listRelawan?.setOnLongClickListener(OnLongClickListener { view ->

            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            
            alert.setItems(action) { dialog, i ->
                when(i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("dataNama", items.get(position).nama)
                        bundle.putString("dataEmail", items.get(position).email)
                        bundle.putString("dataTelp", items.get(position).telp)
                        bundle.putString("dataAlamat", items.get(position).alamat)
                        bundle.putString("getPrimaryKey", items.get(position).key)
                        val intent = Intent(context, RelawanUpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }

                    1 -> {
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID : String = auth?.currentUser?.uid.toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Relawan")
                                .child(items.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
            }

            alert.create()
            alert.show()
            true

        })

    }
}