
 package com.rick.drawabless

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import com.rick.drawabless.Consts.Companion.MAX_SOUNDS
import com.rick.drawabless.Consts.Companion.SOUNDS_FOLDER
import com.rick.drawabless.Consts.Companion.TAG
import java.io.IOException
import java.lang.Exception

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
            .setMaxStreams(MAX_SOUNDS)
            .build()
    init {
        sounds = loadSounds()
    }

/* Assets are accessed using the AssetManager class.
*
*
*
* */

//list(String) lists filenames contained in the folder path you pass in. only .wav
    private fun loadSounds(): List<Sound> {

        val soundNames: Array<String>
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception) {
            Log.e("tag", "Could not list assets", e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                loadSounds(sound)
                sounds.add(sound)
            }catch (ioe: IOException){ Log.e(TAG, "Could not load aound $filename", ioe)}
        }
        Log.i("tag", "${sounds.toString()}")
        return sounds
    }

    private fun loadSounds(sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }


    fun play(sound: Sound, volume: Float){
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 0, 0, volume)
            Log.e("tag", "$it, the song")
        }
    }

    fun release() {
        soundPool.release()
    }








}

/*
* Importing Assets
*
* to add the sound files to the project rather than use the resources system for this job, we'll use raw assets.
* You can think of assets as stripped-down resources: They are packaged into you APK like resources, but without
* any of the configuration system tooling that goes on top of resources.
*
* In some ways, that is good. Because there is no configuration system, you can name assets whatever
you want and organize them with your own folder structure. In other ways, though, it is bad. Without
a configuration system, you cannot automatically respond to changes in pixel density, language, or
orientation, nor can you automatically use the assets in layout files or other resources.
*
* Usually resources are the better deal. However, in cases where you only access files programmatically,
* assets can come out ahead.
* */

/*
* Accessing Assets
*
*
* */