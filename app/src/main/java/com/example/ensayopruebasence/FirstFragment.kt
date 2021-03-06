package com.example.ensayopruebasence

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ensayopruebasence.viewmodel.EnsayoViewModel

class FirstFragment:Fragment(), FirstFragmentListAdapter.OnProductSelectListener {

    private lateinit var vModel:EnsayoViewModel
    private lateinit var recyView:RecyclerView
    private lateinit var adapter: FirstFragmentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = ViewModelProvider(this).get(EnsayoViewModel::class.java)
        adapter = FirstFragmentListAdapter(mutableListOf(), this)
        vModel.productList.observe(this, Observer { adapter.update(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.first_fragment_layout, container, false)
        recyView = view.findViewById(R.id.recycler)
        recyView.layoutManager = LinearLayoutManager(context)
        recyView.adapter = adapter

        return view
    }

    override fun onProductSelected(id: Int) {
        var inten = Intent(context, SecondActivity::class.java)
        inten.putExtra("ID", id)
        startActivity(inten)
        (context as MainActivity).finish()
    }
}