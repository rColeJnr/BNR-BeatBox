package com.rick.drawabless

import androidx.lifecycle.ViewModel

class SoundViewModel(private val beatBox: BeatBox) {




    fun onButtonClicked(sound1: Sound, volume: Float) {

        sound1?.let {
            beatBox.play(sound1, volume)
        }
    }

    var sound: Sound? = null
        set(sound) {
            field = sound
        }

    val title: String?
        get() = sound?.name











}