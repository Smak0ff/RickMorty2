package com.example.rickmorty2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty2.R
import com.example.rickmorty2.databinding.FragmentMainBinding
import com.example.rickmorty2.databinding.RecyclerCharacterItemBinding
import com.example.rickmorty2.ui.pagedList.CharactersAdapter
import com.example.rickmorty2.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(R.layout.fragment_main) {
    private lateinit var mBinding: FragmentMainBinding
    private lateinit var adapter: CharactersAdapter
    private lateinit var mRecyclerView: RecyclerView
    private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(layoutInflater)
        adapter = CharactersAdapter()
        mRecyclerView = mBinding.recyclerViewId
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mainFragmentViewModel.charactersPagedListLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}