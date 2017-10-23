package com.bowerswilkins.nestedfragmenttransitions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.ParentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showFragmentNumeric()
        }
    }

    @CallSuper
    override fun onBackPressed() {
        val topFragment = fragmentManager.findFragmentById(R.id.activity_main_container)
        if (topFragment is ParentFragment) {
            if (topFragment.handledBackPress()) {
                return
            }
        }
        if (fragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

    fun showFragmentNumeric() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fragment_nothing,
                                     R.animator.fragment_nothing,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)
                .add(R.id.activity_main_container, FragmentNumeric(), "FragmentNumeric")
                .addToBackStack("FragmentNumeric")
                .commit()

    }

    fun showFragmentAlpha() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fragment_enter,
                                     R.animator.fragment_exit,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)
                .replace(R.id.activity_main_container, FragmentAlpha(), "FragmentAlpha")
                .addToBackStack("FragmentAlpha")
                .commit()
    }
}
