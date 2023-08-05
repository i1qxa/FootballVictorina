package com.winl.ine.foot.footballvictorina.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.winl.ine.foot.footballvictorina.R
import kotlin.random.Random

class GameViewModel : ViewModel() {

    val listOfCountries = listOf<Country>(
        Country(1, "Germany"),
        Country(2, "Italy")
    )

    val listOfPlayers = listOf<Player>(
        Player(
            "Karl-Heinz Rummenigge",
            1,
            R.drawable.rummenige_club_ramka,
            R.drawable.rummenige_country_ramka
        ),
        Player(
            "Gerd Müller ",
            1,
            R.drawable.gerd_muller_club_ramka,
            R.drawable.gerd_muller_country_ramka
        ),
        Player(
            "Oliver Rolf Kahn",
            1,
            R.drawable.oliver_kahn_club_ramka,
            R.drawable.oliver_kahn_country_ramka
        ),
        Player(
            "Rudolf Völler",
            1,
            R.drawable.feller_club_ramka,
            R.drawable.feller_country_ramka
        ),
        Player(
            "Bastian Schweinsteiger",
            1,
            R.drawable.sweinsteiger_club_ramka,
            R.drawable.sweinsteiger_country_ramka
        ),
        Player(
            "Paolo Rossi",
            2,
            R.drawable.rossi_club_ramka,
            R.drawable.rossi_country_ramka
        ),
        Player(
            "Gianluigi Buffon",
            2,
            R.drawable.buffon_club_ramka,
            R.drawable.buffon_country_ramka
        ),
        Player(
            "Paolo Cesare Maldini",
            2,
            R.drawable.maldini_club_ramka,
            R.drawable.maldini_country_ramka
        ),
        Player(
            "Roberto Baggio",
            2,
            R.drawable.roberto_badgio_club_ramka,
            R.drawable.roberto_badgio_country_ramka
        ),
        Player(
            "Fabio Cannavaro",
            2,
            R.drawable.canavarro_club_ramka,
            R.drawable.canavarro_country_ramka
        ),
    )

    val listOfRemainingPlayers: MutableList<Player> = listOfPlayers.toMutableList()

    private val _currentQuestion = MutableLiveData<Question>()

    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    val currentCountry: Int
        get() {
            return Random.nextInt(listOfCountries.size)
        }



}