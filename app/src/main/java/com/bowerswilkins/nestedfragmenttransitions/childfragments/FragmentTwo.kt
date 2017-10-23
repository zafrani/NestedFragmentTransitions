package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric

class FragmentTwo : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentTwo"
    }

    override fun getTextViewText(): String {
        return "Fragment Two"
    }

    override fun getButtonText(): String {
        return "Show Fragment Three"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (parentFragment as FragmentNumeric).showFragmentThree()
        }
    }

}