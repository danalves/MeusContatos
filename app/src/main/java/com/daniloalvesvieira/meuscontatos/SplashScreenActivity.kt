package com.daniloalvesvieira.meuscontatos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        carregar()
    }

    fun carregar() {

        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)

        anim.reset()

        if(ivLogo != null) {
            ivLogo.clearAnimation()
            ivLogo.startAnimation(anim)
        }

        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(intent)
            this@SplashScreenActivity.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }
}
