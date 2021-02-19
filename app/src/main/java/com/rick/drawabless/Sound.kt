package com.rick.drawabless

import com.rick.drawabless.Consts.Companion.WAV

class Sound(val assetPath: String, var soundId: Int? = null) {
    val name = assetPath.split("/").last().removeSuffix(WAV)

    /*FIRST YOu split off the filename using String.split(String).last()*/














}

/*
* Responsible for keeping track of the filename, the name the user should see, and any other information
* related to that sound
* */