package srimani7.apps.bmi.calculator

fun Double.format(places: Int) = "%.${places}f".format(this)