package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bowerswilkins.nestedfragmenttransitions.R

class FragmentView(context: Context) : FrameLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.fragment_child, this)
    }

    @Suppress("unused")
    fun getXFraction(): Float {
        if (this.width == 0) {
            return 0f
        }
        return this.x / this.width
    }

    @Suppress("unused")
    fun setXFraction(xFraction: Float) {
        this.x = if (this.width > 0) xFraction * this.width else -9999f
    }
}
