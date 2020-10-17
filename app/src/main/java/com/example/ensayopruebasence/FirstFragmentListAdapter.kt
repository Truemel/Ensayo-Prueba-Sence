package com.example.ensayopruebasence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ensayopruebasence.model.room.EnsayoRoomProduct
import com.squareup.picasso.Picasso

class FirstFragmentListAdapter(var list: MutableList<EnsayoRoomProduct>, var onProdSelListener:OnProductSelectListener):RecyclerView.Adapter<FirstFragmentListAdapter.Holder>(),
    View.OnClickListener {

    class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var name:TextView
        lateinit var price:TextView
        lateinit var image:ImageView
    }

    fun update(list: MutableList<EnsayoRoomProduct>){
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.product_list_layout, parent, false)
        var holder:Holder = Holder(view)
        holder.name = view.findViewById(R.id.name_prod)
        holder.price = view.findViewById(R.id.price_prod)
        holder.image = view.findViewById(R.id.image_prod)
        view.setOnClickListener(this)

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.tag = list[position].id
        holder.name.text = list[position].name
        holder.price.text = list[position].price.toString()
        Picasso.get().load(list[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(v: View?) {
        onProdSelListener.onProductSelected(v!!.tag as Int)
    }

    interface OnProductSelectListener{
        fun onProductSelected(id:Int)
    }
}