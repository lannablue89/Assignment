package com.nab.assignment

import android.os.Bundle
import android.widget.Toast
import com.nab.assignment.databinding.ActivityMainBinding
import com.stericson.RootTools.RootTools
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

        if (RootTools.isAccessGiven()) {
            Toast.makeText(this, "Your app has been granted root access!!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}