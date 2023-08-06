package com.winl.ine.foot.footballvictorina.game

data class Question(
    val listOfVariants:List<Player>,
    val listOfRightAnswers:List<Player>,
){
    val question:List<Player> = (listOfRightAnswers + listOfVariants).shuffled()
}
