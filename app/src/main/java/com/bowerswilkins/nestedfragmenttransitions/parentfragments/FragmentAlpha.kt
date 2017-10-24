package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.os.Bundle
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentA
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentB
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentC

class FragmentAlpha : ParentFragment() {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (childFragmentManager.backStackEntryCount <= 1) {
                showFragmentA()
            }
        }
    }

    override fun getTagName(): String {
        return "FragmentAlpha"
    }

    fun showFragmentA() {
        addFragment(FragmentA())
    }

    fun showFragmentB() {
        replaceFragment(FragmentB())

    }

    fun showFragmentC() {
        replaceFragment(FragmentC())

    }

}