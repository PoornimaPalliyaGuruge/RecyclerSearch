package com.example.recyclersearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class ActSearch : AppCompatActivity() {

    lateinit var textView : TextView
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        val heading = intent.getStringExtra("heading")
        textView = findViewById(R.id.textView)

        // Set the text of the TextView with the heading value
        textView.text = heading

        //log screen
        val screenViewBundle = Bundle()
        screenViewBundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "ActSearch")
        screenViewBundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ActSearch")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, screenViewBundle)


    }
}
