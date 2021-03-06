package com.nab.assignment.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nab.assignment.R
import com.nab.assignment.databinding.FragmentWeatherBinding
import com.nab.assignment.model.Resource.Companion.isError
import com.nab.assignment.model.Status
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Lan Tran on 1/31/21.
 */

class WeatherFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: WeatherViewModel by activityViewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentWeatherBinding

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)

        val adapter = WeatherAdapter()
        binding.run {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            btnSubmit.setOnClickListener {
                viewModel.fetchSearchData(edSearch.text.toString())
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchData()
            }
        }

        viewModel.data.observe(viewLifecycleOwner) {
            if (it.status == Status.LOADING) {
                binding.swipeRefreshLayout.isRefreshing = true
                binding.btnSubmit.isEnabled = false
            } else {
                binding.swipeRefreshLayout.isRefreshing = false
                binding.btnSubmit.isEnabled = true
                adapter.submitList(it.data)

                if (it.isError()) {
                    Toast.makeText(context, it.error!!.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}