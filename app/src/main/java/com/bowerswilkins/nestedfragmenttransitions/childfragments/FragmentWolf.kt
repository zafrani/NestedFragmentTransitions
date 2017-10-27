package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.content.Intent
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.MainActivity
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.FragmentNumeric

class FragmentWolf : ChildFragment() {

    override fun getTagName(): String {
        return "FragmentWolf"
    }

    override fun getTextViewText(): String {
        return "Fragment Wolf"
    }

    override fun getButtonText(): String {
        return "Restart"
    }

    override fun getButtonAction(): View.OnClickListener {
        return View.OnClickListener {
            (activity as MainActivity).startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()

        }
    }

}