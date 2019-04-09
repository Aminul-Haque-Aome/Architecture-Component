package com.remotearth.fake_coder.architecturecomponent.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.remotearth.fake_coder.architecturecomponent.viewModels.MainViewModel
import com.remotearth.fake_coder.architecturecomponent.R
import com.remotearth.fake_coder.architecturecomponent.models.Repository
import com.remotearth.fake_coder.architecturecomponent.adapters.RepositoryRecyclerViewAdapter
import com.remotearth.fake_coder.architecturecomponent.callbacks.OnItemClickCallback
import com.remotearth.fake_coder.architecturecomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(
            this
        ).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main)

        binding.apply {
            viewModel = mainViewModel
            executePendingBindings()

            repositoryRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            repositoryRecyclerView.adapter = repositoryRecyclerViewAdapter
        }

        mainViewModel.repositories.observe(this, Observer<ArrayList<Repository>> {
            it?.let {
                repositoryRecyclerViewAdapter.notifyDataSetChanged()
            }
        })

    }

    override fun onItemClicked(data: Repository) {
        Toast.makeText(this, data.repositoryName, Toast.LENGTH_SHORT).show()
    }
}
