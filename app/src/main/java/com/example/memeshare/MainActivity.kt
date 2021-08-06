package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bLoadMeme = findViewById<Button>(R.id.bLoadMeme)
        bLoadMeme.setOnClickListener {
            loadMeme()
        }
        val bSendMeme = findViewById<Button>(R.id.bSendMeme)
        bSendMeme.setOnClickListener {
                sendMeme()
            }
        }
    lateinit var link:String
    fun loadMeme()
    {
        val ivMeme = findViewById<ImageView>(R.id.ivMeme)
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener{ response ->
                link = response.getString("url")
                Glide.with(this).load(link).into(ivMeme)
            },
            Response.ErrorListener { Toast.makeText(this, "Could not load meme.", Toast.LENGTH_SHORT).show()})
        queue.add(jsonRequest)
    }
    fun sendMeme()
    {
        var sendMeme = Intent(Intent.ACTION_SEND).also{
            it.type = "text/plain"
            it.putExtra(Intent.EXTRA_TEXT, link)
        }
        startActivity(sendMeme)
    }
}