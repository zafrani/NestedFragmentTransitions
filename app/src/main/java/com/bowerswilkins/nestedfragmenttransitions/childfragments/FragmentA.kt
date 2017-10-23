package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric

class FragmentA : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentA"
    }

    override fun getTextViewText(): String {
        return "Fragment A"
    }

    override fun getButtonText(): String {
        return "Show Fragment B"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (parentFragment as FragmentAlpha).showFragmentB()
        }
    }

}