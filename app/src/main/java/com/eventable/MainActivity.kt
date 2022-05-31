package com.eventable

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "test msg")

        Log.e("TAG", "Ein anderer Test")

        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)




//        val db = Firebase.firestore


     //--> Neuer User wird unter der Sammlung users mit automatisch generierte ID angelegt
//// Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Christopher",
//            "last" to "Augustin",
//            "born" to 1997
//        )
//// Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }

//   --> Alle Daten in Sammlung auslesen und ausgeben

//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }

        // --> Hier werden Daten mit festen Pfad in DB geschrieben
        //--> ListOf ist auch möglich (Arrays)
 //       val cities = db.collection("cities")

//        val data1 = hashMapOf(
//            "name" to "San Francisco",
//            "state" to "CA",
//            "country" to "USA",
//            "capital" to false,
//            "population" to 860000,
//            "regions" to listOf("west_coast", "norcal")
//        )
//        cities.document("name").set(data1)

//        // Create a reference to the cities collection
//        val nameRef = db.collection("users")
//
//        // Create a query against the collection.
//        val query = nameRef.whereEqualTo("first", "Christopher")
//
//        Log.e("TAG", "$query")
//        Log.e("TAG", "query")





//        db.collection("users")
//            .whereEqualTo("first", "Christopher")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents: ", exception)
//            }













    }
}