package com.example.flashcardv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.collections.ArrayList

class CreateFlashCard : AppCompatActivity() {

    lateinit var btnSave : Button
    lateinit var setCardFront : TextInputEditText
    lateinit var setCardBack : TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_flash_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSave = findViewById(R.id.btnSaveCard)
        setCardFront = findViewById(R.id.inTxtFront)
        setCardBack = findViewById(R.id.inTxtBack)

        btnSave.setOnClickListener{ onClickSaveCard()}
    }


    fun onClickSaveCard(){

        var flashcardList: ArrayList<Flashcard>? = intent.getParcelableArrayListExtra("flashcard_list")

        var cardFront : String = setCardFront.text.toString()
        var cardBack : String = setCardBack.text.toString()

        val flashcard = Flashcard(front = cardFront, back = cardBack)

        flashcardList?.add(flashcard)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("flashcard_list", flashcardList)
        startActivity(intent)


    }
}