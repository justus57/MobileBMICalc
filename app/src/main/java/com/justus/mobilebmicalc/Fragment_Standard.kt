package com.justus.mobilebmicalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment__imperial.*
import kotlinx.android.synthetic.main.fragment__imperial.ReCalculate
import kotlinx.android.synthetic.main.fragment__imperial.bmi
import kotlinx.android.synthetic.main.fragment__imperial.bmi_tv
import kotlinx.android.synthetic.main.fragment__imperial.calculate_btn
import kotlinx.android.synthetic.main.fragment__imperial.etWeight
import kotlinx.android.synthetic.main.fragment__imperial.status
import kotlinx.android.synthetic.main.fragment__standard.*
import android.R
import kotlinx.android.synthetic.main.fragment__standard.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Standard.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_Standard : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(com.justus.mobilebmicalc.R.layout.fragment__standard, container, false)

            view.calculate_btn!!.setOnClickListener(View.OnClickListener {
            // Check if the height EditText and Weight EditText are not empty
            if (feet.text.isNotEmpty() && etWeight.text.isNotEmpty()&&inches.text.isNotEmpty()) {

                val feet = (feet.text.toString()).toInt()
                val inches = (inches.text.toString()).toInt()
                val weight = (etWeight.text.toString()).toInt()
                val inchlen = 12 * feet
                val heightf =inchlen + inches
                val poundweight = weight * 0.454
                // calculateBMI will return BMI
                val BMI = fragment_Imperial.calculateBMI(heightf, poundweight.toInt())

                bmi.text = BMI.toString()
                bmi.visibility = View.VISIBLE

                // update the status text as per the bmi conditions
                if (BMI < 18.5) {
                    status.text = "Under Weight"
                } else if (BMI >= 18.5 && BMI < 24.9) {
                    status.text = "Healthy"
                } else if (BMI >= 24.9 && BMI < 30) {
                    status.text = "Overweight"
                } else if (BMI >= 30) {
                    status.text = "Suffering from Obesity"
                }
                status.visibility = View.VISIBLE
                calculate_btn.visibility = View.GONE
            }

            // when either Weight EditText or
            // height EditText have null value
            // we will display toast.
            else {
                val show = "please enter the valid height and weight"
                Toast.makeText(context, show, Toast.LENGTH_SHORT)
                    .show()
            }
        })

                    view.ReCalculate.setOnClickListener {
            ResetEverything()
        }
         return  view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_Standard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_Standard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)

                }
            }
    }

    private fun ResetEverything() {

        calculate_btn.visibility = View.VISIBLE
        ReCalculate.visibility = View.GONE

        etHeight.text.clear()
        etWeight.text.clear()
        status.text = " "
        bmi.text = " "
        bmi_tv.visibility = View.GONE
    }
    // Function to reset all Text and EditText fields.
    private fun calculateBMI(height: Int, weight: Int): Float {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)
        return BMI
    }

    // Function to reset all Text and EditText fields.

}


