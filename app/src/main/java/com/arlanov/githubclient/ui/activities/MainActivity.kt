package com.arlanov.githubclient.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.arlanov.githubclient.R
import com.arlanov.githubclient.ui.fragments.ShowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fr, ShowFragment.newInstance("",""))
            .commit();
    }
}
