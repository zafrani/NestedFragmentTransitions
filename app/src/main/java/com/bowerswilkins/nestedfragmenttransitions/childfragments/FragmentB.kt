package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha

class FragmentB : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentB"
    }
    override fun getTextViewText(): String {
        return "Fragment B"
    }

    override fun getButtonText(): String {
        return "Show Fragment C"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (parentFragment as FragmentAlpha).showFragmentC()
        }
    }

}