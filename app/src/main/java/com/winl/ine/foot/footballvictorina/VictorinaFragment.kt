package com.winl.ine.foot.footballvictorina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.winl.ine.foot.footballvictorina.databinding.FragmentVictorinaBinding
import com.winl.ine.foot.footballvictorina.game.VictorinaGameFragment

class VictorinaFragment : Fragment() {

    private lateinit var binding: FragmentVictorinaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorinaBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlay.setOnClickListener {
            launchGameFragment()
        }
    }

    private fun launchGameFragment(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, VictorinaGameFragment())
            .addToBackStack("WelcomeFragment")
            .commit()
    }
}