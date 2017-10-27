package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.content.Intent
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.MainActivity
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha

class FragmentC : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentC"
    }

    override fun getTextViewText(): String {
        return "Fragment C"
    }

    override fun getButtonText(): String {
        return "Show Fragment K9"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (activity as MainActivity).showFragmentK9()
        }
    }

}