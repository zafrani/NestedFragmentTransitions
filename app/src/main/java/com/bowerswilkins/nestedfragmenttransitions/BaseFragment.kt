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
    var suppressEnter = false
    var suppressExit = false
    var isPopping = false
    var isConfigChange = false

    //endregion

    //region Fragment

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator {
        if (isConfigChange) {
            Log.e(javaClass.simpleName, "isConfigChange")
            isConfigChange = false
            return nothingAnim()
        }
        if (parentFragment is ParentFragment) {
            if ((parentFragment as ParentFragment).isPopping) {
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
                isPopping = false
                return pushAnim()
            }
            if (suppressEnter) {
                Log.e(javaClass.simpleName, "suppressEnter")
                this.suppressEnter = false
                return nothingAnim()
            }
            Log.e(javaClass.simpleName, "enter")
            return enterAnim()
        }

        if (isPopping) {
            Log.e(javaClass.simpleName, "isPopping")
            isPopping = false
            return popAnim()
        }

        if (suppressExit) {
            Log.e(javaClass.simpleName, "suppressExit")
            this.suppressExit = false
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