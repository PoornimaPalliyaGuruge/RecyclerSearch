package com.example.recyclersearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var button1: Button
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList : ArrayList<Accounts>
    lateinit var imageId : ArrayList<Int>
    lateinit var heading : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        button1 = findViewById(R.id.button)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "some_test")

        // Log the "SELECT_CONTENT" event
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        // Set click listener for the button
        button1.setOnClickListener {
            // Log the "button_click" event with parameters
            val buttonBundle = Bundle()
            buttonBundle.putString("button_name", "Items")
            firebaseAnalytics.logEvent("button_click", buttonBundle)

            // Define the target activity
            val intent = Intent(this@MainActivity, ActSearch::class.java)

            // Start the new activity
            startActivity(intent)
        }

        imageId = arrayListOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j
        )

        heading = arrayListOf(
            "Item 01",
            "Item 02",
            "Item 03",
            "Item 04",
            "Item 05",
            "Item 06",
            "Item 07",
            "Item 08",
            "Item 09",
            "Item 10",
        )

        newRecyclerView = findViewById(R.id.rv)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Accounts>()
        getUserData()

    }

    private fun getUserData() {

        for(i in imageId.indices){
            val account =Accounts(imageId[i],heading[i])
            newArrayList.add(account)
        }

        newRecyclerView.adapter = MyAdapter(newArrayList,Firebase.analytics) { heading ->
            // Handle item click
            val intent = Intent(this@MainActivity, ActSearch::class.java)
            intent.putExtra("heading", heading)
            startActivity(intent)
        }
    }
}
