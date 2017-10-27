package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.GlobalClickListener
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric

class FragmentOne : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentOne"
    }

    override fun getTextViewText(): String {
        return "Fragment One"
    }

    override fun getButtonText(): String {
        return "Show Fragment Two"
    }

    override fun getButtonAction(): GlobalClickListener {
        return GlobalClickListener(View.OnClickListener {
            (parentFragment as FragmentNumeric).showFragmentTwo()
        }, duration)
    }

}