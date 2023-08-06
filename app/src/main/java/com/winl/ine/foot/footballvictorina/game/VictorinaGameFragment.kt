package com.winl.ine.foot.footballvictorina.game

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.winl.ine.foot.footballvictorina.R
import com.winl.ine.foot.footballvictorina.databinding.FragmentVictorinaGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        observeViewModel()
    }
    private fun observeViewModel(){
        viewModel.currentQuestion.observe(viewLifecycleOwner){ it ->
            hidePlayersName()
            with(binding.imagePlayer1){
                setImageResource(it.question[0].clubImg)
                setOnClickListener{ question ->
                    viewModel.checkAnswer(it.question[0])
                    lifecycleScope.launch {
                        flipCard(
                            imgView = binding.imagePlayer1,
                            imgEnd = it.question[0].countryImg,
                            tvPlayerName = binding.tvPlayer1Name,
                            player = it.question[0]
                        )
                    }
                }
            }
            with(binding.imagePlayer2){
                setImageResource(it.question[1].clubImg)
                setOnClickListener{ question ->
                    lifecycleScope.launch {
                        flipCard(
                            imgView = binding.imagePlayer2,
                            imgEnd = it.question[1].countryImg,
                            tvPlayerName = binding.tvPlayer2Name,
                            player = it.question[1]
                        )
                    }
                }
            }
            with(binding.imagePlayer3){
                setImageResource(it.question[2].clubImg)
                setOnClickListener{ question ->
                    lifecycleScope.launch {
                        flipCard(
                            imgView = binding.imagePlayer3,
                            imgEnd = it.question[2].countryImg,
                            tvPlayerName = binding.tvPlayer3Name,
                            player = it.question[2]
                        )
                    }
                }
            }
            with(binding.imagePlayer4){
                setImageResource(it.question[3].clubImg)
                setOnClickListener{ question ->
                    lifecycleScope.launch {
                        flipCard(
                            imgView = binding.imagePlayer4,
                            imgEnd = it.question[3].countryImg,
                            tvPlayerName = binding.tvPlayer4Name,
                            player = it.question[3]
                        )
                    }
                }
            }
        }

        viewModel.answerLD.observe(viewLifecycleOwner){
           binding.tvCountRightAnswers.text = "Count of right Answers: ${it.getCountOfRightAnswers()}/2"
        }

        viewModel.currentCountryLD.observe(viewLifecycleOwner){
            binding.tvQuestion.text = it
        }
    }

    private fun hidePlayersName(){
        with(binding){
            tvPlayer1Name.visibility = View.GONE
            tvPlayer2Name.visibility = View.GONE
            tvPlayer3Name.visibility = View.GONE
            tvPlayer4Name.visibility = View.GONE
        }
    }


    private suspend fun flipCard(imgView:ImageView, imgEnd:Int,tvPlayerName:TextView, player:Player) {
        val flipOutAnimatorSet =
            AnimatorInflater.loadAnimator(
                requireContext(),
                R.animator.anim_out
            ) as AnimatorSet
        flipOutAnimatorSet.setTarget(imgView)
        val flipInAnimationSet =
            AnimatorInflater.loadAnimator(
                context,
                R.animator.anim_in
            ) as AnimatorSet
        flipInAnimationSet.setTarget(imgView)
        flipOutAnimatorSet.start()
        withContext(Dispatchers.Main) {
            delay(300)
            imgView.setImageResource(imgEnd)
            flipInAnimationSet.start()
            delay(2000)
            tvPlayerName.text = player.name
            tvPlayerName.visibility = View.VISIBLE
            viewModel.checkAnswer(player)
            delay(1000)
            viewModel.shouldStartNextRound()
        }
    }
}