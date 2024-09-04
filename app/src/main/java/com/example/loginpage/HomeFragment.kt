package com.example.loginpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val items = listOf(
            "Coding like poetry should be short and concise. ― Santosh Salwar",
            "It’s not a bug; it’s an undocumented feature. ― Anonymous",
            "First, solve the problem. Then, write the code. – John Johnson",
            "Code is like humor. When you have to explain it, it’s bad. – Cory House",
            "Make it work, make it right, make it fast. – Kent Beck",
            "Clean code always looks like it was written by someone who cares. — Robert C. Martin",
            "Of course, bad code can be cleaned up. But it’s very expensive.” — Robert C. Martin",
            "Confusion is part of programming. ― Feline Herman's",
            "Programmer: A machine that turns coffee into code. – Anonymous",
            "Programming is learned by writing programs. ― Brian Kernighan",
            "When I wrote this code, only God and I understood what I did. Now only God knows.  – Anonymous",
            "I’m not a great programmer; I’m just a good programmer with great habits. ― Kent Beck"
        )
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = HomeAdapter(items)
        recyclerView.adapter = adapter

        return view
    }
}