package com.palak.starwarsdemo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.palak.starwarsdemo.MainActivity
import com.palak.starwarsdemo.R
import com.palak.starwarsdemo.databinding.FragmentCharaceterDetailBinding
import com.palak.starwarsdemo.models.Character

/**
 * Screen that loads details of the character.
 */
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharaceterDetailBinding

    private var character: Character? = null
    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_characeter_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        character = args.character
        binding.character = character
    }
}