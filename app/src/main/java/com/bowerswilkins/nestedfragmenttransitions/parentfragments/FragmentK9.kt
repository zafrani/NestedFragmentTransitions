package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.os.Bundle
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.childfragments.*

class FragmentK9 : ParentFragment() {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (this.childFragmentManager.backStackEntryCount <= 0) {
                showFragmentDog()
            }
        }
    }

    override fun getTagName(): String {
        return "FragmentK9"
    }


    fun showFragmentDog() {
        addFragment(FragmentDog())
    }

    fun showFragmentWolf() {
        replaceFragment(FragmentWolf())
    }

}