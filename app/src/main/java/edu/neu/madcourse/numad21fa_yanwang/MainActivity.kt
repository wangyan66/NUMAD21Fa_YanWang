package edu.neu.madcourse.numad21fa_yanwang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        val clickyBtn: Button = findViewById(R.id.button2)
        clickyBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val linkCollectorBtn: Button = findViewById(R.id.link_collector_Btn)
        linkCollectorBtn.setOnClickListener {
            val intent = Intent(this, LinkCollectorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.remove_item -> Toast.makeText(this, "Remove Something!", Toast.LENGTH_SHORT).show()
            R.id.exit_item -> finish()
            R.id.add_item -> Toast.makeText(this, "Add Something!", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}