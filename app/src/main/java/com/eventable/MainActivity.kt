package com.eventable

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View.inflate
import android.webkit.HttpAuthHandler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    lateinit var eingabeEdTe: EditText
    lateinit var abschickenBtn: Button
    lateinit var anzeigen1 : TextView
    lateinit var anzeigen2 : TextView
    lateinit var anzeigen3 : TextView
   // lateinit var anzeigen4 : Button
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "test msg")


        //val anzeigen1 : TextView = findViewById(R.id.anzeigen1)
        val anzeigen2 : TextView = findViewById(R.id.anzeigen2)
        val anzeigen3 : TextView = findViewById(R.id.anzeigen3)
        val eingabeEdTe : EditText = findViewById(R.id.eingabeEdTe)
        val eingabe2EdTe : EditText = findViewById(R.id.eingabe2EdTe)
        val abschickenBtn : Button = findViewById(R.id.abschickenBtn)
        var anzeigen4 : Button = findViewById(R.id.anzeigen4Btn)

        val db = Firebase.firestore

        //val intent = Intent(this, LogInActivity::class.java) //Man benötigt Intent für Wechsel auf andere Activity
        //startActivity(intent) //Start der Activity

        abschickenBtn.setOnClickListener{

        }


        //Einfügen von Werten
        //Wenn DocumentPath gleich bleibt, dann können Daten aktuallisiert werden,
        //Firebase erstellt kein eigenes Dokument, passt nur die veränderte Spalte an
         val veranstaltungen = db.collection("Veranstaltungen")
         val data1 = hashMapOf(
            "Datum" to "05.06.2022",
            "ID" to "123456",
            "Name" to eingabeEdTe.text.toString(),
            "Standort" to "Vereinsheim",
            "Fragen" to listOf("Bist du Fahrer?", "Hast du einen Grill?"),
            "Teilnehmer" to listOf("Christopher@augustin.de")
        )
      //  veranstaltungen.document("123456").set(data1)



        abschickenBtn.setOnClickListener{
            val firstName = eingabeEdTe.text.toString()
            val lastName = eingabe2EdTe.text.toString()
            saveUser(firstName, lastName)

        }
       // readData()
       // readDataWithQuery()


       val db1 = FirebaseFirestore.getInstance()
       val result: StringBuffer = StringBuffer()
        db1.collection("veranstaltungen")
            .whereEqualTo("ID", "123456")
            .get()
            .addOnCompleteListener {


                if (it.isSuccessful) {
                    for (document in it.result) {
                        result.append(document.data.getValue("Name"))

                    }
                    anzeigen4.setText(result)

                }
            }

        anzeigen4.setOnClickListener{
            Toast.makeText(this@MainActivity, "$result", Toast.LENGTH_LONG).show()
        }


            }
    //Speichern von bestimmten Daten
     fun saveUser(firstName: String, lastName: String) {
        val db1 = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = firstName
        user["lastName"] = lastName

        db1.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@MainActivity, "record added successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@MainActivity, "records added failed", Toast.LENGTH_SHORT).show()
            }
    }

    //Lesen von bestimmten Daten
//    fun readData() {
//
//        anzeigen1 = findViewById(R.id.anzeigen1)
//
//        val db1 = FirebaseFirestore.getInstance()
//        db1.collection("users")
//            .get()
//            .addOnCompleteListener{
//                val result: StringBuffer = StringBuffer()
//
//                if(it.isSuccessful){
//                    for(document in it.result!!){
//                        result.append(document.data.getValue("firstName")).append(" ")
//                            .append(document.data.getValue("lastName")).append("\n\n")
//                    }
//                    anzeigen1.setText(result)
//                }
//            }
//    }


//    fun readDataWithQuery() {
//        anzeigen2 = findViewById(R.id.anzeigen2)
//        anzeigen3 = findViewById(R.id.anzeigen3)
//        //anzeigen4 = findViewById(R.id.anzeigen4Btn)
//
//        val db = FirebaseFirestore.getInstance()
//        db.collection("veranstaltungen")
//            .whereEqualTo("ID", "123456")
//            .get()
//            .addOnCompleteListener{
//                val result: StringBuffer = StringBuffer()
//                val result2: StringBuffer = StringBuffer()
//                val result3: StringBuffer = StringBuffer()
//
//                if(it.isSuccessful){
//                    for(document in it.result){
//                        result.append("Ort: ").append(document.data.getValue("Name"))
//                        result2.append("Datum: ").append(document.data.getValue("Datum"))
//                        result3.append("ID: ").append(document.data.getValue("ID"))
//
//                    }
//                    anzeigen2.setText(result)
//                    anzeigen3.setText(result2)
//                    anzeigen4.setText(result3)
//                }
//            }
//    }


}
