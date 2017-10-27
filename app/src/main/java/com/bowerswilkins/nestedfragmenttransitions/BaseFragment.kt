package com.bowerswilkins.nestedfragmenttransitions

import android.animation.Animator
import android.animation.AnimatorInflater
import android.app.Fragment
import android.util.Log
import com.bowerswilkins.nestedfragmenttransitions.parentfragments.ParentFragment

abstract class BaseFragment : Fragment() {

    //region Static

    //endregion

    //region Fields

    private var isSuppressing = false
    private var isPopping = false
    private var isConfigChange = false

    //endregion

    //region Fragment

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator {
        val anim: Animator
        if (this.isConfigChange) {
            anim = nothingAnim()
        } else if (isParentFragmentRemovingOrPopping()) {
            anim = nothingAnim()
        } else if (isSuppressing) {
            anim = nothingAnim()
        } else if (enter) {
            anim = when {
                this.isPopping -> pushAnim()
                else -> enterAnim()
            }
        } else {
            anim = when {
                this.isPopping -> popAnim()
                else -> exitAnim()
            }
        }
        resetStates()
        return anim
/*
        if (isConfigChange) {
            Log.e(javaClass.simpleName, "isConfigChange")
            resetStates()
            return nothingAnim()
        }

        if (parentFragment is ParentFragment) {
            if ((parentFragment as BaseFragment).isPopping) {
                Log.e(javaClass.simpleName, "parentFragment is Popping")
                return nothingAnim()
            }
        }

        if (parentFragment != null && parentFragment.isRemoving) {
            Log.e(javaClass.simpleName, "parentFragment is Removing")
            return nothingAnim()
        }

        if (enter) {
            if (isPopping) {
                Log.e(javaClass.simpleName, "isPopping")
                resetStates()
                return pushAnim()
            }
            if (isSuppressing) {
                Log.e(javaClass.simpleName, "isSuppressing")
                resetStates()
                return nothingAnim()
            }
            Log.e(javaClass.simpleName, "enter")
            return enterAnim()
        }

        if (isPopping) {
            Log.e(javaClass.simpleName, "isPopping")
            resetStates()
            return popAnim()
        }

        if (isSuppressing) {
            Log.e(javaClass.simpleName, "isSuppressing")
            resetStates()
            return nothingAnim()
        }

        Log.e(javaClass.simpleName, "exit")
        return exitAnim()*/
    }

    //endregion

    //region Abstract

    abstract fun getTagName(): String

    //endregion

    //region Methods
    private fun isParentFragmentRemovingOrPopping(): Boolean {
        return (parentFragment != null && parentFragment.isRemoving) || // Checks if parent fragment is being removed.
               ((parentFragment is ParentFragment) && (parentFragment as BaseFragment).isPopping) // Checks if parent fragment is being popped.
    }


    open fun setSuppressing() {
        this.isSuppressing = true
    }

    open fun setPopping() {
        this.isPopping = true
    }

    open fun setConfigChange() {
        this.isConfigChange = true
    }

    private fun resetStates() {
        this.isPopping = false
        this.isConfigChange = false
        this.isSuppressing = false
    }

    open protected fun enterAnim(): Animator {
        Log.e(javaClass.simpleName, "enterAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_enter)
    }

    open protected fun exitAnim(): Animator {
        Log.e(javaClass.simpleName, "exitAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_exit)
    }

    open protected fun pushAnim(): Animator {
        Log.e(javaClass.simpleName, "pushAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_push)
    }

    open protected fun popAnim(): Animator {
        Log.e(javaClass.simpleName, "popAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_pop)
    }

    open protected fun nothingAnim(): Animator {
        Log.e(javaClass.simpleName, "nothingAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_nothing)
    }

    //endregion

}