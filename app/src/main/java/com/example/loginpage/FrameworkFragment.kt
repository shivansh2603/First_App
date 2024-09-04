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
import com.example.loginpage.FrameWorkPhoto

class FrameworkFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragments_frameworks,container,false)

        val recyclerView : RecyclerView = view.findViewById(R.id.frameworksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val frameworks = listOf(
            FrameWorkPhoto("Android SDK", R.drawable.icons8_android_100),
            FrameWorkPhoto("Retrofit", R.drawable.icons8_withdrawal_50),
            FrameWorkPhoto("Room", R.drawable.icons8_room_48),
            FrameWorkPhoto("Jetpack Compose", R.drawable.icons8_jetpack_50, ),
            FrameWorkPhoto("SwiftUI", R.drawable.icons8_swiftui_50,),
            FrameWorkPhoto("Flutter", R.drawable.icons8_flutter_50, ),
            FrameWorkPhoto("React Native",
                R.drawable.icons8_nativescript_is_an_open_source_framework_to_develop_apps_24, ),
            FrameWorkPhoto("UIKit",
                R.drawable.icons8_uikit_a_lightweight_and_modular_front_end_framework_for_developing_fast_and_powerful_web_interfaces__24, ),
            FrameWorkPhoto("Ionic", R.drawable.icons8_ionic_50, ),
            FrameWorkPhoto("NativeScript", R.drawable.icons8_native_50,),
            FrameWorkPhoto("Felgo", R.drawable.icons8_happy_50,),
            FrameWorkPhoto("Cordova", R.drawable.icons8_cordova_48,),
            FrameWorkPhoto("Xamarin", R.drawable.icons8_xmas_64),
            FrameWorkPhoto("PhoneGap", R.drawable.icons8_phone_50,),
        )
        val adapter = FrameworksAdapter(frameworks)
        recyclerView.adapter = adapter

        return view
    }
}