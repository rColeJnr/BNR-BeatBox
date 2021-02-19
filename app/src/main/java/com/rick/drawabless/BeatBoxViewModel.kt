package com.rick.drawabless

import android.content.res.AssetManager
import android.content.res.loader.AssetsProvider
import androidx.lifecycle.ViewModel

class BeatBoxViewModel(private val assets: AssetManager): ViewModel() {

    val beatBox: BeatBox = BeatBox(assets)

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }
}