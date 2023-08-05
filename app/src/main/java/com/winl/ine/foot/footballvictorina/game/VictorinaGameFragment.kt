package com.winl.ine.foot.footballvictorina.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.winl.ine.foot.footballvictorina.R
import com.winl.ine.foot.footballvictorina.databinding.FragmentVictorinaGameBinding

class VictorinaGameFragment : Fragment() {

    private lateinit var binding:FragmentVictorinaGameBinding
    private val viewModel by lazy { ViewModelProvider(this)[GameViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorinaGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}