package edu.neu.madcourse.numad21fa_yanwang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this, "Name:Yan Wang\nEmail:wang.yan6@northeastern.edu", Toast.LENGTH_SHORT).show()
        }
    }
}