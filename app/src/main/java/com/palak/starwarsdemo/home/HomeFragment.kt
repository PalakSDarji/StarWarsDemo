package com.palak.starwarsdemo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.palak.starwarsdemo.R
import com.palak.starwarsdemo.databinding.FragmentHomeBinding
import com.palak.starwarsdemo.home.paging.LoaderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var adapter: CharacterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        loadCharacterList()
    }

    private fun loadCharacterList() {
        binding.rvCharacters.also {
            it.layoutManager = LinearLayoutManager(context)
            adapter = CharacterAdapter { _, pos ->

            }

            it.adapter = adapter?.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
            it.isNestedScrollingEnabled = false
            it.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        homeViewModel.characterList.observe(viewLifecycleOwner){
            adapter?.submitData(lifecycle,it)
        }

        adapter?.addLoadStateListener {
            binding.hasData = adapter?.itemCount!! > 0
        }

        binding.tryAgain.setOnClickListener {
            adapter?.retry()
        }
    }
}