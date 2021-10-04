package edu.neu.madcourse.numad21fa_yanwang

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
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
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Field

class LinkCollectorActivity : AppCompatActivity(), OnRecycleViewClickListener{
    private val LinkList = ArrayList<LinkItem>()
    override fun onItemClickListener(pos: Int) {
        val item : LinkItem = LinkList[pos]
        val intent = Intent(Intent.ACTION_VIEW)
        var url : String = item.Url
        if(url.startsWith("www")){
            url = "https://" + url
        }else if(!url.startsWith("https://") && !url.startsWith("http://")){
            url = "https://www" + url
        }
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_collector)

        initLinks()
        val layoutManger = LinearLayoutManager(this)
        val adapter = LinkAdapter(LinkList)
        adapter.setListener(this)
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
                            LinkList.add(0,LinkItem(nameStr,urlStr))
                            adapter.notifyItemInserted(0)
                            Snackbar.make(it, "Add Link Successfully!", Snackbar.LENGTH_SHORT).show()
//                            Toast.makeText(this@LinkCollectorActivity, "Add Link Successfully!", Toast.LENGTH_SHORT).show()
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
//
    }

}