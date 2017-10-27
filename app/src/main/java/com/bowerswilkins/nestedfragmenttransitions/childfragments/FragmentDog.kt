package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.GlobalClickListener
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentAlpha
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentK9

class FragmentDog : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentDog"
    }

    override fun getTextViewText(): String {
        return "Fragment Dog"
    }

    override fun getButtonText(): String {
        return "Show Fragment Wolf"
    }

    override fun getButtonAction(): GlobalClickListener {
        return GlobalClickListener(View.OnClickListener {
            (parentFragment as FragmentK9).showFragmentWolf()
        }, duration)
    }

}