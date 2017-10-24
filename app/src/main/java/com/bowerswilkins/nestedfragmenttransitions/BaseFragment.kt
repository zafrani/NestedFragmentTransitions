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
        return exitAnim()
    }

    //endregion

    //region Abstract

    abstract fun getTagName(): String

    //endregion

    //region Methods

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

    private fun enterAnim(): Animator {
        Log.e(javaClass.simpleName, "enterAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_enter)
    }

    private fun exitAnim(): Animator {
        Log.e(javaClass.simpleName, "exitAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_exit)
    }

    private fun pushAnim(): Animator {
        Log.e(javaClass.simpleName, "pushAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_push)
    }

    private fun popAnim(): Animator {
        Log.e(javaClass.simpleName, "popAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_pop)
    }

    private fun nothingAnim(): Animator {
        Log.e(javaClass.simpleName, "nothingAnim")
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_nothing)
    }

    //endregion

}