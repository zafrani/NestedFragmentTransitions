package com.bowerswilkins.nestedfragmenttransitions

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import android.util.Log
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
        val topFragment = getTopFragment()
        if (topFragment is ParentFragment) {
            if (topFragment.handledBackPress()) {
                return
            }
            topFragment.isPopping = true
        }
        if (fragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        val secondFragmentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2)
        val secondFragment = fragmentManager.findFragmentByTag(secondFragmentEntry.name)
        if (secondFragment is ParentFragment) {
            secondFragment.isPopping = true
        }
        super.onBackPressed()
    }

    fun showFragmentNumeric() {
        val fragment = FragmentNumeric()
        fragment.suppressEnter = true
        fragmentManager.beginTransaction()
                /*.setCustomAnimations(R.animator.fragment_nothing,
                                     R.animator.fragment_nothing,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)*/
                .add(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()

    }

    fun showFragmentAlpha() {
        val fragment = FragmentAlpha()
        fragmentManager.beginTransaction()
                /*.setCustomAnimations(R.animator.fragment_enter,
                                     R.animator.fragment_exit,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)*/
                .replace(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun getTopFragment(): Fragment? {
        val topfragment = fragmentManager.findFragmentById(R.id.activity_main_container)
        Log.e(javaClass.simpleName, "topFragment = " + (topfragment?.toString() ?: "null"))
        return topfragment
    }
}
