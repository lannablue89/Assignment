package com.nab.assignment

import android.os.Bundle
import com.nab.assignment.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Lan Tran on 1/31/21.
 */

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}