package com.rick.drawabless

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SeekBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rick.drawabless.databinding.ActivityMainBinding
import com.rick.drawabless.databinding.ListItemSoundBinding

class BeatBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingBtn: ListItemSoundBinding
    private lateinit var soundViewModel: SoundViewModel
    private lateinit var beatBox: BeatBox
    private var volume: Float = 0f

    private var callbacks: Callbacks? = null

    interface Callbacks{
        fun onSoundClick(soundId: Sound)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        beatBox = BeatBoxViewModel(assets).beatBox
        soundViewModel = SoundViewModel(beatBox)

        binding.rvBeatBox.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }

        binding.seekbar.apply {
            setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    volume = progress.toFloat()/10
                    binding.tvSeekbar.text = "Playback Speed $volume%"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    return
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    return
                }
            })
        }

    }

//    fun buttonClick(view: View){
//        soundViewModel.onButtonClicked(volume)
//        Log.i("tag","button pressec, ${soundViewModel.title}")
//    }


    private inner class SoundAdapter(private val sounds: List<Sound>) :
            RecyclerView.Adapter<SoundAdapter.SoundHolder>() {

        inner class SoundHolder(bindingSound: ListItemSoundBinding):
            RecyclerView.ViewHolder(bindingSound.root) {


            fun bindSound(sound: Sound){
                    soundViewModel.sound = sound
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            bindingBtn = ListItemSoundBinding.inflate(layoutInflater, parent, false)
            return SoundHolder(bindingBtn)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bindSound(sound)
            holder.itemView.apply {
                bindingBtn.btnSound.text = soundViewModel.title
                bindingBtn.btnSound.setOnClickListener {
//                    buttonClick(this)
                    soundViewModel.onButtonClicked(sound, volume)
                }
            }


        }

        override fun getItemCount() = sounds.size
    }






















}







