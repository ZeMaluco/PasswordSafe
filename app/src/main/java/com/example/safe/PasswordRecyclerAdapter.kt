package com.example.safe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safe.data.Password
import com.example.safe.PasswordRecyclerAdapter.PasswordViewHolder as PasswordViewHolder1

class PasswordRecyclerAdapter(private val myDataset: List<Password>) :
    RecyclerView.Adapter<PasswordViewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder1 {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return PasswordViewHolder1(v)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: PasswordViewHolder1, position: Int) {
        holder.pweb.text = myDataset[position].website
        holder.password.text = myDataset[position].password
        holder.pDesc.text = myDataset[position].description
    }

    class PasswordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pweb: TextView = itemView.findViewById(R.id.pweb)
        var password: TextView = itemView.findViewById(R.id.password)
        var pDesc: TextView = itemView.findViewById(R.id.pDesc)
    }


}

