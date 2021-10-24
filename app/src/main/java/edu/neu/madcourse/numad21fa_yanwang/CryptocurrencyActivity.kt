package edu.neu.madcourse.numad21fa_yanwang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

class CryptocurrencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency)
        val sendBtn : Button = findViewById(R.id.button9)
        sendBtn.setOnClickListener {
            sendRequestWithHttpURLConnection()
        }

    }
    private fun sendRequestWithHttpURLConnection(){
        thread {
            var connection : HttpsURLConnection ?  = null
            try {
                val response = StringBuilder()
                val url = URL("https://api.coinpaprika.com/v1/coins/btc-bitcoin")
                connection = url.openConnection() as HttpsURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream

                val  reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())


            }catch (e : Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!")
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }
    private fun showResponse(response : String){
        runOnUiThread {
            // do UI update operation

            val textView : TextView = findViewById(R.id.textView3)
            textView.text = response
        }
    }
}