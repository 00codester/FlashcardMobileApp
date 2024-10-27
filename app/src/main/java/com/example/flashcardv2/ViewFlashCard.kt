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
        val flashcardList: ArrayList<Flashcard>? = intent.getParcelableArrayListExtra("flashcard_list")
        val flashcard: Flashcard? = intent.getParcelableExtra("flashcard_data")
        val cardFaceDisplay: TextView = findViewById(R.id.txtCard)
        var flipcard: Button = findViewById(R.id.flipCard)
        val homeBtn: Button = findViewById(R.id.btnHome)

        flashcard?.let {
            cardFaceDisplay.text = flashcard.front

            flipcard.setOnClickListener{
                if (isFrontVisible) {
                    cardFaceDisplay.text = flashcard.back
                } else {
                    cardFaceDisplay.text = flashcard.front
                }
                isFrontVisible = !isFrontVisible
            }
        }

        homeBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("flashcard_list", flashcardList)
            }
            startActivity(intent)
            finish()
        }

    }
}