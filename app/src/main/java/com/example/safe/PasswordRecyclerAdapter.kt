package com.example.safe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        var currentWeb = myDataset[position].website
        var currentPass = myDataset[position].password
        var currentDesc = myDataset[position].description
        var id = myDataset[position].id



        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                val activity = v!!.context as AppCompatActivity
                val newPasswordFragment = NewPasswordFragment.newInstance(id!!,currentWeb,currentPass,currentDesc)
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.flContent, newPasswordFragment).addToBackStack(null).commit()


            }
        })
    }

    class PasswordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pweb: TextView = itemView.findViewById(R.id.pweb)
        var password: TextView = itemView.findViewById(R.id.password)
        var pDesc: TextView = itemView.findViewById(R.id.pDesc)
    }



}

