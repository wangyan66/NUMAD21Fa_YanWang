package edu.neu.madcourse.numad21fa_yanwang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread


private const val TAG = "CryptocurrencyActivity"
class CryptocurrencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency)
        val sendBtn : Button = findViewById(R.id.button9)
        changeProgressBar()
        sendBtn.setOnClickListener {
            sendRequestWithOkHttp()
            changeProgressBar()
        }

    }
    private fun changeProgressBar(){
        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        if(progressBar.visibility == View.VISIBLE){
            progressBar.visibility = View.GONE
        }else{
            progressBar.visibility = View.VISIBLE
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
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://api.coinpaprika.com/v1/coins/doge-dogecoin/ohlcv/today/")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData != null){
                    parseJSONWithGSON(responseData)
                }
            }catch (e : Exception){
                println("!!!!!!!!!!!!!!!!!!!!!!")
                e.printStackTrace()
            }
        }
    }
    private fun parseJSONWithGSON(jsonData : String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<CryptoCurrency>>() {}.type
        val coinList = gson.fromJson<List<CryptoCurrency>>(jsonData, typeOf)
//        val coin = gson.fromJson(jsonData, CryptoCurrency::class.java)
        for (coin in coinList){
            Log.d(TAG, "close is ${coin.close}")
        }
        runOnUiThread {
            val textView : TextView = findViewById(R.id.textView3)
            textView.text = jsonData
            changeProgressBar()
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