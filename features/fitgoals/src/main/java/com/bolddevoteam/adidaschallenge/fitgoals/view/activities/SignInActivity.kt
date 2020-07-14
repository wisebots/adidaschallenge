package com.bolddevoteam.adidaschallenge.fitgoals.view.activities

import android.content.Intent
import android.os.Bundle
import com.bolddevoteam.adidaschallenge.core.base.BaseActivity
import com.bolddevoteam.adidaschallenge.core.commons.GOALS_PERMISSION
import com.bolddevoteam.adidaschallenge.core.extensions.checkGoogleFitPermissions
import com.bolddevoteam.adidaschallenge.fitgoals.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.Scope
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.defaultSharedPreferences

class SignInActivity : BaseActivity() {

    val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 999

    override fun getLayout() = R.layout.activity_sign_in

    private var googleFitPermissionGranted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkGoogleFitPermissions(fitnessOptions, this)

        googleSignInButton.apply {
            setSize(SignInButton.SIZE_STANDARD)
            setOnClickListener {
                val intent = getGoogleSignInClient().signInIntent
                startActivityForResult(intent, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
            }
        }
    }

    private fun getGoogleSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(Scopes.FITNESS_ACTIVITY_READ))
            .addExtension(fitnessOptions)
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    override fun onResume() {
        super.onResume()
        if (googleFitPermissionGranted) finish()
        else {
            checkGoogleFitPermissions(fitnessOptions, this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log("resultCode $resultCode")
        if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account: GoogleSignInAccount? = task.result
            account?.let {
                log("account ${it.email}")
                defaultSharedPreferences.edit().putBoolean(GOALS_PERMISSION, true).apply()
            }
            googleFitPermissionGranted = true
        }
    }
}

