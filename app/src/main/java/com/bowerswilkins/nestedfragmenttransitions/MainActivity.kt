package com.bowerswilkins.nestedfragmenttransitions

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.CallSuper
import android.util.Log
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentK9
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
        /* val secondFragmentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2)
         if (secondFragmentEntry != null) {
             val secondFragment = fragmentManager.findFragmentByTag(secondFragmentEntry.name)
             if (secondFragment is ParentFragment) {
                 secondFragment.setPopping()
             }
         }*/
        get2ndToLastParentFragment()?.setPopping()
        super.onBackPressed()
    }

    //endregion

    //region Methods

    fun get2ndToLastParentFragment(): ParentFragment? {
        if (fragmentManager.backStackEntryCount > 1) {
            val secondFragmentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2)
            val secondFragment = fragmentManager.findFragmentByTag(secondFragmentEntry.name)
            if (secondFragment is ParentFragment) {
                Log.e(javaClass.simpleName, "secondToLastParentFragment = " + secondFragment.getTagName())
                return secondFragment
            }

        }
        return null
    }

    fun showFragmentNumeric() {
        val fragment = FragmentNumeric()
        fragment.setSuppressing()
        fragmentManager.beginTransaction()
                .add(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()

    }

    fun showFragmentAlpha() {
        fragmentManager.findFragmentByTag("FragmentNumeric")?.let {
            if (it is FragmentNumeric) {
                it.suppress = true
            }
        }

        val fragment = FragmentAlpha()
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_container, fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun showFragmentK9() {
        fragmentManager.findFragmentByTag("FragmentAlpha")?.let {
            if (it is FragmentAlpha) {
                it.slideDown = true
            }
        }
        fragmentManager.findFragmentByTag("FragmentNumeric")?.let {
            if (it is FragmentNumeric) {
                it.suppressAll()
            }
        }

        fragmentManager.popBackStackImmediate()
        Handler().postDelayed({
                                  val fragment = FragmentK9()
                                  fragmentManager.beginTransaction()
                                          .replace(R.id.activity_main_container, fragment, fragment.getTagName())
                                          .addToBackStack(fragment.getTagName())
                                          .commit()
                              },
                              resources.getInteger(R.integer.duration).toLong())

    }

    fun getTopFragment(): Fragment? {
        val topfragment = fragmentManager.findFragmentById(R.id.activity_main_container)
        Log.e(javaClass.simpleName, "topFragment = " + (topfragment?.toString() ?: "null"))
        return topfragment
    }

    //endregion
}
