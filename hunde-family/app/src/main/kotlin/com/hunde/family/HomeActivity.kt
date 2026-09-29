package com.hunde.family

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerViewUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. DURA `setContentView` WAAMUU DIRQAMA
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // RecyclerView & Adapter Initialize Gochuu
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(ArrayList())
        recyclerViewUsers.adapter = userAdapter

        // Barbaacha (Search) Mul'isuu / Hissuu
        btnSearch?.setOnClickListener {
            if (etSearch?.visibility == View.GONE) {
                etSearch.visibility = View.VISIBLE
            } else {
                etSearch?.visibility = View.GONE
            }
        }

        // Gara Settings Deemuuf
        btnSettings?.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Dynamic Users Firebase irraa dubbisuu
        loadFirebaseUsers()
    }

    private fun loadFirebaseUsers() {
        val currentUserId = auth.currentUser?.uid
        val usersRef = FirebaseDatabase.getInstance().getReference("users")

        usersRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userList = ArrayList<User>()
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        // Akkaawuntii ammaa (current user) akka itti hin mul'anneef:
                        if (user?.uid != currentUserId) {
                            user?.let { userList.add(it) }
                        }
                    }
                    // Adapter keetti userList kennei display gochuu
                    userAdapter.updateList(userList)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Dogoggora yoo uumame
                }
        })
    }
}
