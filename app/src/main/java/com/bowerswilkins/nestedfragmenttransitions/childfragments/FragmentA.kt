package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.GlobalClickListener
import com.bowerswilkins.nestedfragmenttransitions.R
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

    override fun getButtonAction(): GlobalClickListener {
        return GlobalClickListener(View.OnClickListener {
            (parentFragment as FragmentAlpha).showFragmentB()
        }, duration)
    }
}