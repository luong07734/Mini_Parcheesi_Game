package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
var state = "Roll"

class MainActivity : AppCompatActivity() {

    private var xDelta = 0
    private var yDelta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpHorses()
        binding.button.setOnClickListener{
            rollAndStop()
        }
        rollAllDices()

    }

    private fun setUpHorses()
    {
        // red
        val layoutParams = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewRed1.layoutParams = layoutParams
        //setup touch listener
        binding.imageViewRed1.setOnTouchListener(CustomTouchListener())

        val layoutParams2 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewRed2.layoutParams = layoutParams2
        //setup touch listener
        binding.imageViewRed2.setOnTouchListener(CustomTouchListener())

        val layoutParams3 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewRed3.layoutParams = layoutParams3
        //setup touch listener
        binding.imageViewRed3.setOnTouchListener(CustomTouchListener())

        val layoutParams4 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewRed4.layoutParams = layoutParams4
        //setup touch listener
        binding.imageViewRed4.setOnTouchListener(CustomTouchListener())

        // blue
        val layoutParams5 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewBlue1.layoutParams = layoutParams5
        //setup touch listener
        binding.imageViewBlue1.setOnTouchListener(CustomTouchListener())

        val layoutParams6 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewBlue2.layoutParams = layoutParams6
        //setup touch listener
        binding.imageViewBlue2.setOnTouchListener(CustomTouchListener())

        val layoutParams7 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewBlue3.layoutParams = layoutParams7
        //setup touch listener
        binding.imageViewBlue3.setOnTouchListener(CustomTouchListener())

        val layoutParams8 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewBlue4.layoutParams = layoutParams8
        //setup touch listener
        binding.imageViewBlue4.setOnTouchListener(CustomTouchListener())

        val layoutParams9 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewYellow1.layoutParams = layoutParams9
        //setup touch listener
        binding.imageViewYellow1.setOnTouchListener(CustomTouchListener())

        val layoutParams10 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewYellow2.layoutParams = layoutParams10
        //setup touch listener
        binding.imageViewYellow2.setOnTouchListener(CustomTouchListener())

        val layoutParams11 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewYellow3.layoutParams = layoutParams11
        //setup touch listener
        binding.imageViewYellow3.setOnTouchListener(CustomTouchListener())

        val layoutParams12 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewYellow4.layoutParams = layoutParams12
        //setup touch listener
        binding.imageViewYellow4.setOnTouchListener(CustomTouchListener())

        val layoutParams13 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewGreen1.layoutParams = layoutParams13
        //setup touch listener
        binding.imageViewGreen1.setOnTouchListener(CustomTouchListener())

        val layoutParams14 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewGreen2.layoutParams = layoutParams14
        //setup touch listener
        binding.imageViewGreen2.setOnTouchListener(CustomTouchListener())

        val layoutParams15 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewGreen3.layoutParams = layoutParams15
        //setup touch listener
        binding.imageViewGreen3.setOnTouchListener(CustomTouchListener())

        val layoutParams16 = RelativeLayout.LayoutParams(80, 80)
        binding.imageViewGreen4.layoutParams = layoutParams16
        //setup touch listener
        binding.imageViewGreen4.setOnTouchListener(CustomTouchListener())
    }


    private inner class CustomTouchListener : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = v.layoutParams as RelativeLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }
                MotionEvent.ACTION_UP -> {
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                }
                MotionEvent.ACTION_POINTER_UP -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    v.layoutParams = layoutParams
                }
            }
            binding.relative.invalidate()
            return true
        }
    }

    private fun rollAndStop(){
        if(state == "Roll")
        {
            val anim = RotateAnimation(
                0.0f,
                360.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )

//Setup anim with desired properties

            anim.interpolator = LinearInterpolator()
            anim.repeatCount = Animation.INFINITE //Repeat animation indefinitely
            anim.duration = 200 //Put desired duration per anim cycle here, in milliseconds
            binding.imageView.startAnimation(anim)
            binding.imageView2.startAnimation(anim)
            state = "Stop"
            binding.button.text = getString(R.string.stop)
        }
        else
        {
            binding.imageView.animation = null
            binding.imageView2.animation = null
            rollAllDices()
            state = "Roll"
            binding.button.text = getString(R.string.roll)
        }

    }

    private fun rollAllDices()
    {
        rollDice(binding.imageView)
        rollDice(binding.imageView2)
    }

    private fun rollDice(imageView: ImageView) {
        val dice = Dice(6)
        val imageResult = when (dice.roll()) {
            1 -> {
                R.drawable.dice_1
            }
            2 -> {
                R.drawable.dice_2
            }
            3 -> {
                R.drawable.dice_3
            }
            4 -> {
                R.drawable.dice_4
            }
            5 -> {
                R.drawable.dice_5
            }
            else -> {
                R.drawable.dice_6
            }
        }
        imageView.setImageResource(imageResult)
        imageView.contentDescription = null
    }
}



class Dice(private val sides: Int)
{
    fun roll(): Int{
        return (1..sides).random()
    }
}