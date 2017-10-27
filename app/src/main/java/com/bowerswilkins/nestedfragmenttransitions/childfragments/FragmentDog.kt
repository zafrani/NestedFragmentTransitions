package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.view.View
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

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (parentFragment as FragmentK9).showFragmentWolf()
        }
    }

}