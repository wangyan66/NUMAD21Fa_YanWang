package edu.neu.madcourse.numad21fa_yanwang

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.reflect.Field

class LinkCollectorActivity : AppCompatActivity() {
    private val LinkList = ArrayList<LinkItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_collector)

        initLinks()
        val layoutManger = LinearLayoutManager(this)
        val adapter = LinkAdapter(LinkList)
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManger
        recyclerView.adapter = adapter

        val addBtn: FloatingActionButton = findViewById(R.id.add_link_Btn)
        addBtn.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Input Link")
                val contentView : View = LayoutInflater.from(this@LinkCollectorActivity).inflate(R.layout.link_input_layout, null, false)
                setView(contentView)
                val linkName : EditText = contentView.findViewById(R.id.link_name)
                val linkUrl : EditText = contentView.findViewById(R.id.link_url)
                setPositiveButton("OK"){ diaglog : DialogInterface , _ ->
                    val nameStr : String = linkName.text.toString().trim()
                    val urlStr : String = linkUrl.text.toString().trim()
//                    Toast.makeText(this@LinkCollectorActivity, nameStr + "\n" + urlStr, Toast.LENGTH_SHORT).show()

                    try {
                        if(!Patterns.WEB_URL.matcher(urlStr).matches()){
                            val field : Field = diaglog.javaClass.superclass.superclass.getDeclaredField("mShowing")
                            field.isAccessible = true
                            field.set(diaglog, false)
                            Toast.makeText(this@LinkCollectorActivity, "Invalid Url", Toast.LENGTH_SHORT).show()
                        }else{
                            val field : Field = diaglog.javaClass.superclass.superclass.getDeclaredField("mShowing")
                            field.isAccessible = true
                            field.set(diaglog, true)
                        }

                    }catch (e : IllegalAccessException){
                        e.printStackTrace()
                    }catch (e : NoSuchFieldException){
                        e.printStackTrace()
                    }
                }
                setNegativeButton("Cancel"){ dialog : DialogInterface, _ ->
                    try {
                        val field : Field = dialog.javaClass.superclass.superclass.getDeclaredField("mShowing")
                        field.isAccessible = true
                        field.set(dialog, true)
//                        Toast.makeText(this@LinkCollectorActivity, "Invalid Url", Toast.LENGTH_SHORT).show()

                    }catch (e : IllegalAccessException){
                        e.printStackTrace()
                    }catch (e : NoSuchFieldException){
                        e.printStackTrace()
                    }
                }
                show()
            }
        }
    }
    private fun initLinks(){
        repeat(9){
            LinkList.add(LinkItem("bilibli","www.bilibili.com"))
            LinkList.add(LinkItem("baidu","www.baidu.com"))
            LinkList.add(LinkItem("Google","www.google.com"))
        }
    }

}