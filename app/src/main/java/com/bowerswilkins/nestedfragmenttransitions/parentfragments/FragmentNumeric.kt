package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.app.FragmentManager
import android.os.Bundle
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentOne
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentThree
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentTwo


class FragmentNumeric : ParentFragment() {
    var suppress = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (this.childFragmentManager.backStackEntryCount <= 0) {
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

    override fun exitAnim(): Animator {
        if (suppress) {
            this.suppress = false
            return AnimatorInflater.loadAnimator(activity, R.animator.fragment_nothing_below)
        }
        return super.exitAnim()
    }

}