package com.example.rickmorty2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickmorty2.databinding.ActivityMainBinding
import com.example.rickmorty2.ui.fragment.MainFragment
import org.koin.androidx.fragment.android.replace
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //Koin Fragment Factory
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        //Переход на MainFragment через койновский replace
        supportFragmentManager.beginTransaction().replace<MainFragment>(R.id.fragmentWindowId).commit()

    }
}