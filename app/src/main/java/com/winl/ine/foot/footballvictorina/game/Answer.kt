package com.winl.ine.foot.footballvictorina.game

class Answer(){
    var firstAnswer:Boolean? = null
    var secondAnswer:Boolean? = null
    fun addAnswer(result:Boolean){
        if (firstAnswer == null){
            firstAnswer = result
        }else if (secondAnswer == null){
            secondAnswer = result
        }
    }
    fun getCountOfRightAnswers():Int {
        val first = if (firstAnswer?:false) 1 else 0
        val second = if (secondAnswer?:false) 1 else 0
        return first + second
    }
    fun isFullAnswer():Boolean{
        return firstAnswer!=null && secondAnswer != null
    }
}
