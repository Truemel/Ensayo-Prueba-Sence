package com.example.ensayopruebasence

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ensayopruebasence.model.room.EnsayoRoomDetail
import com.example.ensayopruebasence.viewmodel.EnsayoViewModel
import com.squareup.picasso.Picasso

class ProductDetailsFragment(var idProd:Int, context:Context):Fragment() {

    private lateinit var vModel:EnsayoViewModel
    private lateinit var name:TextView
    private lateinit var price:TextView
    private lateinit var image:ImageView
    private lateinit var descrip:TextView
    private lateinit var lastPrice:TextView
    private lateinit var credits:TextView
    private lateinit var detail: EnsayoRoomDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(EnsayoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.details_layout, container, false)
        name = view.findViewById(R.id.name_prod)
        price = view.findViewById(R.id.price_prod)
        image = view.findViewById(R.id.image_prod)
        descrip = view.findViewById(R.id.description)
        lastPrice = view.findViewById(R.id.last_price)
        credits = view.findViewById(R.id.credits)
        vModel.getDetails(idProd)

        vModel.detail.observe(context as SecondActivity, Observer { if(it != null && !it.name.isNullOrEmpty()){name.text = it.name
        price.text = it.price.toString()
        Picasso.get().load(it.image).into(image)
        descrip.text = it.description
        lastPrice.text = it.lastPrice.toString()
        credits.text = it.credit.toString()
        detail = it}})

        return view
    }

    //Envia Email
    fun sendEmail(){
        var inten:Intent = Intent(Intent.ACTION_SEND).apply {
            setData(Uri.parse("mailto:"))
            setType("text/plain")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@plaplix.cl")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Consulta ${detail.name} id ${detail.id}")
            putExtra(Intent.EXTRA_TEXT, "Hola\n" +
                    "Vi el producto ${detail.name} y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número _________\n" +
                    "Quedo atento.")
        }

        startActivity(inten)
        (context as SecondActivity).finish()
    }
}