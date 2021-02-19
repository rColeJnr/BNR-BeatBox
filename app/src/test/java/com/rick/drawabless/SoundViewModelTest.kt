package com.rick.drawabless

import org.hamcrest.CoreMatchers.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {

    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {

        beatBox = mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound

    }

    @Test
    fun exposesSoundNameAstitle() {
        assertThat(subject.title, `is` (sound.name))

        verify(beatBox).play(sound)  //verfy that the play(...) function was called on beatBox with
//        this specific sound as a parameter
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()
    }
}

/*
* building out the interactions between SoundViewModel and your new
BeatBox.play(Sound) function. A common way to go about this is to write a test that shows what you
expect a new function to do before you have written the function.
*
* we want to test if a function that needs a object of another type works, to do that we could
* create another instance, but if there's a problem it the type mother then our viewmodel test will
* fail, and our test should only fail if there's an error in our unit.  unit test
* In other words, you want to test the behavior of SoundViewModel and its interactions with other classes
in isolation. This is a key tenet of unit testing.
*
* The solution is to use a mocked BeatBox . This mock object will be a subclass of BeatBox that has all
the same functions as BeatBox – but none of those functions will do anything. That way, your test of
SoundViewModel can verify that SoundViewModel itself is using BeatBox correctly, without depending
at all on how BeatBox works.
*
* */