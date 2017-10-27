package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.animation.Animator
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.MainActivity
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric

class FragmentThree : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentThree"
    }

    override fun getTextViewText(): String {
        return "Fragment Three"
    }

    override fun getButtonText(): String {
        return "Show Fragment Alpha"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (activity as MainActivity).showFragmentAlpha()
        }

    }

}