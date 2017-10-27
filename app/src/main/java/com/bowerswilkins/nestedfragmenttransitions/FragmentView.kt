package com.bowerswilkins.nestedfragmenttransitions

import android.content.Context
import android.os.Build
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.widget.FrameLayout

abstract class FragmentView(context: Context,
                            @LayoutRes layout: Int) : FrameLayout(context) {
    init {
        LayoutInflater.from(context).inflate(layout, this)
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

    @Suppress("unused")
    fun getYFraction(): Float {
        if (this.height == 0) {
            return 0f
        }
        return this.y / this.height
    }

    @Suppress("unused")
    fun setYFraction(yFraction: Float) {
        this.y = if (this.height > 0) yFraction * this.height else -9999f
    }

    fun getAAlpha(): Float {
        return this.alpha
    }

    fun setAAlpha(aAlpha: Float) {
        this.alpha = aAlpha
    }

    fun getZValue(): Float {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.elevation
        } else {
            return 0f
        }
    }

    fun setZValue(zValue: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.elevation = zValue
        }
    }
}
