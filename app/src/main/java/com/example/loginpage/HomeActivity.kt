package com.example.loginpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolBar : Toolbar = findViewById(R.id.homeToolbar)
        setSupportActionBar(toolBar)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf("Coding like poetry should be short and concise. ― Santosh Kalwar"
            ,"It’s not a bug; it’s an undocumented feature. ― Anonymous"
            ,"First, solve the problem. Then, write the code. – John Johnson"
            ,"Code is like humor. When you have to explain it, it’s bad. – Cory House"
            ,"Make it work, make it right, make it fast. – Kent Beck"
            ,"Clean code always looks like it was written by someone who cares. — Robert C. Martin"
            ,"Of course, bad code can be cleaned up. But it’s very expensive.” — Robert C. Martin"
            ,"Confusion is part of programming. ― Felienne Hermans"
            ,"Programmer: A machine that turns coffee into code. – Anonymous"
            ,"Programming is learned by writing programs. ― Brian Kernighan"
            ,"When I wrote this code, only God and I understood what I did. Now only God knows.  – Anonymous"
            ,"I’m not a great programmer; I’m just a good programmer with great habits. ― Kent Beck")
        val adapter = HomeAdapter(items)
        recyclerView.adapter = adapter
    }
}