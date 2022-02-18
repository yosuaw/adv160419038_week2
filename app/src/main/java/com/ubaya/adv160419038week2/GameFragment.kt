package com.ubaya.adv160419038week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        var num1 = randomNumber()
        var num2 = randomNumber()
        var result = num1 + num2
        var points = 0
        txtNumber.text = "$num1 + $num2"

        btnSubmit.setOnClickListener {
            if(result.toString() == txtAnswer.text.toString()) {
                points++
                num1 = randomNumber()
                num2 = randomNumber()
                result = num1 + num2
                txtNumber.text = "$num1 + $num2"
                txtAnswer.text?.clear()

                Toast.makeText(activity, "Correct! +1 point", Toast.LENGTH_SHORT).show()
            } else {
                val action = GameFragmentDirections.actionResultFragment(points)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun randomNumber():Int {
        return (0..100).random()
    }
}