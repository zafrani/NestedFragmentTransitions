package com.bowerswilkins.nestedfragmenttransitions

import android.view.View

class GlobalClickListener(private val clickListener: View.OnClickListener,
                          private val lockDuration: Long = 0)
    : View.OnClickListener {
    companion object {

        @JvmStatic
        var lastClicked: Long = 0

    }

    override fun onClick(v: View?) {
        if (System.currentTimeMillis() - lastClicked < lockDuration) {
            return
        }
        lastClicked = System.currentTimeMillis()
        clickListener.onClick(v)
    }
}