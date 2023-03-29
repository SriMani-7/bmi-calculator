package srimani7.apps.dailyui

fun Double.format(places: Int) = "%.${places}f".format(this)