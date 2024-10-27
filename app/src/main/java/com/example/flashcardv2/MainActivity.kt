package com.example.flashcardv2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //declare varibles
    lateinit var flashcardContainer: LinearLayout
    var flashcardList = ArrayList<Flashcard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get the xml id of the flashcardcontainer
        flashcardContainer = findViewById(R.id.flashcardContainer)

        flashcardList = intent.getParcelableArrayListExtra<Flashcard>("flashcard_list") ?: ArrayList()

        flashcardList.forEach { addFlashcardToView(it) } ?: run {

        }

        val btnCreateCard: Button = findViewById(R.id.btnAddNewCard)
        btnCreateCard.setOnClickListener{ onClickCreateCard()}

    }

    //when the New Card button is clicked
    fun onClickCreateCard(){
        val intent = Intent(this, CreateFlashCard::class.java)
        intent.putParcelableArrayListExtra("flashcard_list", flashcardList)
        startActivity(intent)
    }

    //puts all the flashcards on the main screen
    fun addFlashcardToView(flashcard: Flashcard){

        val cardfront = TextView(this)
        cardfront.text = flashcard.front
        cardfront.gravity = Gravity.CENTER
        cardfront.textSize = 30f
        cardfront.setPadding(0,30,0,30)
        cardfront.setBackgroundColor(Color.parseColor("#D3D3D3"))

        cardfront.setOnClickListener{
            val intent = Intent(this, ViewFlashCard::class.java).apply{
                putExtra("flashcard_data", flashcard)
                putExtra("flashcard_list", flashcardList)
            }
            startActivity(intent)
        }

        flashcardContainer.addView(cardfront)
    }


}