package com.example.voiceofvoiceless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts : TextToSpeech? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts = TextToSpeech(this,this)



    }
    fun onSpkButtonClick(view:View){
        var input = findViewById<EditText>(R.id.inp)
        if(input.text.isEmpty()){
            Toast.makeText(this,"Enter some text",Toast.LENGTH_SHORT).show()
        }else{
            speak(input.text.toString())
        }
    }
    private fun speak(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_ADD,null,"")
    }
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                Toast.makeText(this,"The TTS is not supported on this device",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Initialization Failed",Toast.LENGTH_LONG).show()
        }
    }

}