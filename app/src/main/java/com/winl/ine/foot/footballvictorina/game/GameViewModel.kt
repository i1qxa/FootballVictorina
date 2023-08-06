package com.winl.ine.foot.footballvictorina.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.winl.ine.foot.footballvictorina.R
import kotlin.random.Random

class GameViewModel : ViewModel() {

    val currentCountry: Int
        get() {
            return Random.nextInt(listOfCountries.size-1)
        }

    val listOfCountries = listOf<Country>(
        Country(0, "Germany"),
        Country(1, "Italy")
    )

    val currentCountryLD = MutableLiveData("Choose 2 players of the ${listOfCountries[currentCountry].name} national football team")

    val listOfPlayers = listOf<Player>(
        Player(
            "Karl-Heinz Rummenigge",
            0,
            R.drawable.rummenige_club_ramka,
            R.drawable.rummenige_country_ramka
        ),
        Player(
            "Gerd Müller ",
            0,
            R.drawable.gerd_muller_club_ramka,
            R.drawable.gerd_muller_country_ramka
        ),
        Player(
            "Oliver Rolf Kahn",
            0,
            R.drawable.oliver_kahn_club_ramka,
            R.drawable.oliver_kahn_country_ramka
        ),
        Player(
            "Rudolf Völler",
            0,
            R.drawable.feller_club_ramka,
            R.drawable.feller_country_ramka
        ),
        Player(
            "Bastian Schweinsteiger",
            0,
            R.drawable.sweinsteiger_club_ramka,
            R.drawable.sweinsteiger_country_ramka
        ),
        Player(
            "Paolo Rossi",
            1,
            R.drawable.rossi_club_ramka,
            R.drawable.rossi_country_ramka
        ),
        Player(
            "Gianluigi Buffon",
            1,
            R.drawable.buffon_club_ramka,
            R.drawable.buffon_country_ramka
        ),
        Player(
            "Paolo Cesare Maldini",
            1,
            R.drawable.maldini_club_ramka,
            R.drawable.maldini_country_ramka
        ),
        Player(
            "Roberto Baggio",
            1,
            R.drawable.roberto_badgio_club_ramka,
            R.drawable.roberto_badgio_country_ramka
        ),
        Player(
            "Fabio Cannavaro",
            1,
            R.drawable.canavarro_club_ramka,
            R.drawable.canavarro_country_ramka
        ),
    )

    val listOfRemainingPlayers: MutableList<Player> =
        listOfPlayers.filter { it.countryId == currentCountry }.shuffled().toMutableList()

    val listOfVariantsPlayers = listOfPlayers.filter { it.countryId!=currentCountry }.shuffled()

    private val _currentQuestion = MutableLiveData<Question>()

    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _shouldFinishGame = MutableLiveData<Any>()
    val shouldFinishGame:LiveData<Any>
        get() = _shouldFinishGame

    private val _answerLD = MutableLiveData(Answer())
    val answerLD:LiveData<Answer>
        get() = _answerLD

    private fun getCurrentQuestion() {
        if (listOfRemainingPlayers.size>=2){
            val firstVariant = Random.nextInt(listOfVariantsPlayers.size)
            var secondVariant = Random.nextInt(listOfVariantsPlayers.size)
            while (secondVariant==firstVariant){
                secondVariant = Random.nextInt(listOfVariantsPlayers.size)
            }
            val listRightAnswers = listOf(listOfRemainingPlayers[0], listOfRemainingPlayers[1])
            val listVariants = listOf(listOfVariantsPlayers[firstVariant], listOfVariantsPlayers[secondVariant])
            listOfRemainingPlayers.removeAt(0)
            listOfRemainingPlayers.removeAt(1)
            _currentQuestion.value = Question(listVariants, listRightAnswers)
            }else {
                _shouldFinishGame.value = Unit
        }
    }

    init {
        getCurrentQuestion()
    }

    fun checkAnswer(player:Player){
        val listRightAnswers = _currentQuestion.value?.listOfRightAnswers
        if (listRightAnswers!=null){
            if (listRightAnswers.contains(player)) _answerLD.value?.addAnswer(true)
            else _answerLD.value?.addAnswer(false)
        }
    }

    fun shouldStartNextRound(){
        if (_answerLD.value?.isFullAnswer()?:false){
            getCurrentQuestion()
            _answerLD.value = Answer()
        }
    }

}