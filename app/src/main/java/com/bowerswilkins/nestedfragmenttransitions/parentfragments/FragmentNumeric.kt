package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.os.Bundle
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentOne
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentThree
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentTwo


class FragmentNumeric : ParentFragment() {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (this.childFragmentManager.backStackEntryCount <= 1) {
                showFragmentOne()
            }
        }
    }

    override fun getTagName(): String {
        return "FragmentNumeric"
    }


    fun showFragmentOne() {
        addFragment(FragmentOne())
    }

    fun showFragmentTwo() {
        replaceFragment(FragmentTwo())
    }

    fun showFragmentThree() {
        replaceFragment(FragmentThree())
    }

}