package com.example.adapterconlista

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioadapterlista1.R
import kotlin.random.Random
import kotlin.random.nextInt

class StringAdapterActivity(var listaString : MutableList<String>) : RecyclerView.Adapter<StringAdapterActivity.StringViewHolder>() {

    class StringViewHolder(var root: View, var textView: TextView, var checkbox: CheckBox) :
        RecyclerView.ViewHolder(root)

    var cont = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val TextView = view.findViewById<TextView>(R.id.textView)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
        return StringViewHolder(view, TextView, checkbox)
    }

    override fun getItemCount(): Int {
        return listaString.size + 3
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        if (position == 0) {
            holder.textView.text = "Borrar"
            if (listaString.size > 0) {
                holder.root.setOnClickListener {
                    listaString.removeAt(Random.nextInt(listaString.size))
                    notifyDataSetChanged()
                }
            }
        } else {

            if (position == listaString.size + 1) {
                holder.textView.text = "Insertar"

                holder.root.setOnClickListener {
                    listaString.add(listaString.last() + "PC-${position}")
                    notifyDataSetChanged()
                }

            } else {

                if (position == listaString.size + 2) {
                    holder.textView.text = "Contar Encendidos: "
                    holder.textView.setOnClickListener {
                        val toast = Toast.makeText(
                            it.context,
                            "Numero de encendidos: ${cont}",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }

                } else {

                    holder.textView.text = "${listaString[position]}"
                }
            }
        }

        for (i in 5..10) {

            val checkAleatorio = Random.nextInt(0..listaString.size)
            if (checkAleatorio == position) {
                holder.checkbox.isChecked = true
            }
        }

        if (holder.checkbox.isChecked) {

            holder.textView.setBackgroundColor(Color.parseColor("#f21630"))
            cont++

        } else {

            holder.textView.setBackgroundColor(Color.parseColor("##33ed0e"))
            cont--
        }

    }
}