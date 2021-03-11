package com.adasta.adastraone.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adasta.adastraone.R
import com.adasta.adastraone.databinding.RecyclerItemBinding
import com.adasta.adastraone.views.CharDetailsActivity
import com.bumptech.glide.Glide


class CharactersAdapter(
    var arrayList: ArrayList<Character.Results>,
    var context: Context
) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return arrayList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (arrayList.size > 0) {
            holder.name.text = arrayList[position].name
//            holder.status.text = "Status: ${arrayList[position].status}"
//            holder.gernder.text = "Gender: ${arrayList[position].gender}"

            Glide.with(context).load(arrayList[position].image).into(holder.avatar)

            holder.itemView.setOnClickListener {
//                context.toast("Hello from ${arrayList.get(position).name}")


                //You asked it in task optional usage :)
                val intent = Intent(context, CharDetailsActivity::class.java)

                intent.putExtra("name", arrayList[position].name)
                intent.putExtra("status", arrayList[position].status)
                intent.putExtra("gender", arrayList[position].gender)
                intent.putExtra("image", arrayList[position].image)
                intent.putExtra("species", arrayList[position].species)

                context.startActivity(intent)

            }
        } else {
            Log.e("Problem", "esle")

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemBinding.bind(itemView)
        val name = binding.tvName
        var avatar = binding.ivImage
    }
}