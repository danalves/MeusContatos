package com.daniloalvesvieira.meuscontatos.firebase.fcm

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.orhanobut.hawk.Hawk

class MeuFirebaseInstanceIdService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        if (Hawk.isBuilt()) {
            Hawk.put("TOKENFIREBASE", FirebaseInstanceId.getInstance().token)
        }

    }
}