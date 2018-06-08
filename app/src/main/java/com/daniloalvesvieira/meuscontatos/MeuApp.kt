package com.daniloalvesvieira.meuscontatos

import android.app.Application
import com.orhanobut.hawk.Hawk

class MeuApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build();
    }
}