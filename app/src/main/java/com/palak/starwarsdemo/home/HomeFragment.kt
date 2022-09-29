package com.palak.starwarsdemo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.palak.starwarsdemo.MainActivity
import com.palak.starwarsdemo.R
import com.palak.starwarsdemo.databinding.FragmentHomeBinding
import com.palak.starwarsdemo.home.paging.LoaderAdapter
import com.palak.starwarsdemo.utils.clearText
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController = findNavController()
        loadCharacterList()
    }

    private fun loadCharacterList() {
        binding.rvCharacters.also {
            it.layoutManager = LinearLayoutManager(context)
            adapter = CharacterAdapter { character, pos ->

                val action =
                    HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(character = character)
                navController.navigate(action)
            }

            it.adapter = adapter?.withLoadStateHeaderAndFooter(
                header = LoaderAdapter {
                    adapter?.retry()
                },
                footer = LoaderAdapter {
                    adapter?.retry()
                }
            )
            it.isNestedScrollingEnabled = false
            it.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        homeViewModel.characterList.observe(viewLifecycleOwner) {
            adapter?.submitData(lifecycle, it)
        }

        adapter?.addLoadStateListener { loadState ->
            Timber.d("STATE : ${loadState.source.refresh}")
            binding.rvCharacters.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
            binding.tvErrorMessage.isVisible = loadState.source.refresh is LoadState.Error
            binding.tvErrorMessage.clearText()
            if (loadState.source.refresh is LoadState.Error) {
                binding.tvErrorMessage.text =
                    (loadState.source.refresh as LoadState.Error).error.localizedMessage
            }
        }

        binding.btnTryAgain.setOnClickListener {
            adapter?.retry()
        }
    }
}