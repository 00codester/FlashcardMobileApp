package com.example.flashcardv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewFlashCard : AppCompatActivity() {

    //determines whether to show the front or back of the flashcard
    var isFrontVisible: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_flash_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declare variables
        val flashcardList: ArrayList<Flashcard>? = intent.getParcelableArrayListExtra("flashcard_list")
        val flashcard: Flashcard? = intent.getParcelableExtra("flashcard_data")
        val cardFaceDisplay: TextView = findViewById(R.id.txtCard)
        val flipCard: Button = findViewById(R.id.flipCard)
        val homeBtn: Button = findViewById(R.id.btnHome)

        //logic to determine which side of the flashcard to show
        flashcard?.let {
            cardFaceDisplay.text = flashcard.front

            flipCard.setOnClickListener{
                if (isFrontVisible) {
                    cardFaceDisplay.text = flashcard.back
                } else {
                    cardFaceDisplay.text = flashcard.front
                }
                isFrontVisible = !isFrontVisible
            }
        }

        //returns user to main screen when done looking at flashcard
        homeBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("flashcard_list", flashcardList)
            }
            startActivity(intent)
            finish()
        }

    }
}