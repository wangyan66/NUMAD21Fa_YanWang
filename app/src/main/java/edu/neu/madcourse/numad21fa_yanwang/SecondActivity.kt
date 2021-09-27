package edu.neu.madcourse.numad21fa_yanwang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity(), View.OnTouchListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val btn3:Button = findViewById(R.id.button3)
        btn3.setOnTouchListener(this)
        val btn4:Button = findViewById(R.id.button4)
        btn4.setOnTouchListener(this)
        val btn5:Button = findViewById(R.id.button5)
        btn5.setOnTouchListener(this)
        val btn6:Button = findViewById(R.id.button6)
        btn6.setOnTouchListener(this)
        val btn7:Button = findViewById(R.id.button7)
        btn7.setOnTouchListener(this)
        val btn8:Button = findViewById(R.id.button8)
        btn8.setOnTouchListener(this)
    }



    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        val textview:TextView = findViewById(R.id.textView2)

        when(p1?.action){
            MotionEvent.ACTION_DOWN ->{
                when(p0?.id){
                    R.id.button3 ->{
                        textview.text = "Pressed: A"
                        return true
                    }
                    R.id.button4 ->{
                        textview.text = "Pressed: B"
                        return true
                    }
                    R.id.button5 ->{
                        textview.text = "Pressed: C"
                        return true
                    }
                    R.id.button6 ->{
                        textview.text = "Pressed: D"
                        return true
                    }
                    R.id.button7 ->{
                        textview.text = "Pressed: E"
                        return true
                    }
                    R.id.button8 ->{
                        textview.text = "Pressed: F"
                        return true
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
                textview.text = "Pressed: -"
                return true
            }
        }
        return false
    }

}