package com.example.practical_21

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.practical_21.databinding.ActivityMainBinding

/**
 * Main Activity of the AboutMe app. This app demonstrates:
 *     * Getting user input with an EditText.
 *     * Click handler for a Button to retrieve text from an EditText and set it in a TextView.
 *     * Setting a click handler on a TextView.
 *     * Setting the visibility status of a view.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Aleks Haecky")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
        binding.myName = myName

        findViewById<TextView>(R.id.nickname_text).setOnClickListener {
            updateNickname(it)
        }


    }

    /**
     * Click handler for the DONE button.
     * Hides the EditText and the DONE button.
     * Sets the EditText content to the TextView and displays it.
     */
    private fun addNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }

    /**
     * Click handler for the nickname TextView.
     * Displays the EditText and the DONE button.
     * Hides the nickname TextView.
     */
    private fun updateNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }


}
