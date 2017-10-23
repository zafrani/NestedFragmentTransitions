package com.bowerswilkins.nestedfragmenttransitions

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import android.util.Log
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.ParentFragment

class MainActivity : AppCompatActivity() {

    //region AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showFragmentNumeric()
        } else {
            val parentFragment = getTopFragment()
            if (parentFragment is ParentFragment) {
                parentFragment.setConfigChange()
                val childFragment = parentFragment.getTopFragment()
                if (childFragment is ChildFragment) {
                    childFragment.setConfigChange()
                }
            }
        }
    }

    @CallSuper
    override fun onBackPressed() {
        Log.e(javaClass.simpleName, "onBackPressed")
        val topFragment = getTopFragment()
        if (topFragment is ParentFragment) {
            if (topFragment.handledBackPress()) {
                return
            }
            topFragment.setPopping()
        }
        if (fragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        val secondFragmentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2)
        val secondFragment = fragmentManager.findFragmentByTag(secondFragmentEntry.name)
        if (secondFragment is ParentFragment) {
            secondFragment.setPopping()
        }
        super.onBackPressed()
    }

    //endregion

    //region Methods

    fun showFragmentNumeric() {
        val fragment = FragmentNumeric()
        fragment.setSuppressing()
        fragmentManager.beginTransaction()
                .add(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()

    }

    fun showFragmentAlpha() {
        val fragment = FragmentAlpha()
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun getTopFragment(): Fragment? {
        val topfragment = fragmentManager.findFragmentById(R.id.activity_main_container)
        Log.e(javaClass.simpleName, "topFragment = " + (topfragment?.toString() ?: "null"))
        return topfragment
    }

    //endregion
}
