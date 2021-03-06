package com.example.ensayopruebasence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {

    private lateinit var fragment:ProductDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title

        fragment = ProductDetailsFragment(this.intent.getIntExtra("ID", 1), this)
        changeFragment(fragment)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { fragment.sendEmail() }
    }

    fun changeFragment(frag:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_second, frag).commit()
    }
}