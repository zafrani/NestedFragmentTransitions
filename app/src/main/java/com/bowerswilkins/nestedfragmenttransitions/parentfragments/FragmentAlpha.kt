package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bowerswilkins.nestedfragmenttransitions.MainActivity
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentA
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentB
import com.bowerswilkins.nestedfragmenttransitions.childfragments.FragmentC

class FragmentAlpha : ParentFragment() {


    var slideDown = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (childFragmentManager.backStackEntryCount <= 0) {
                showFragmentA()
            }
        }
    }

    override fun getTagName(): String {
        return "FragmentAlpha"
    }

    override fun handledBackPress(): Boolean {
        Log.e(javaClass.simpleName, "handledBackPress")
        if (childFragmentManager.backStackEntryCount > 1) {
            return super.handledBackPress()
        }

        if (activity is MainActivity) {
            val previousParentFragment = (activity as MainActivity).get2ndToLastParentFragment()
            if (previousParentFragment is FragmentNumeric) {
                previousParentFragment.setSuppressing()
                previousParentFragment.getTopFragment()?.let {
                    if (it is ChildFragment) {
                        it.setSuppressing()
                    }
                }
                slideDown = true
                return false

            }
        }
        return false
    }

    fun showFragmentA() {
        addFragment(FragmentA())
    }

    fun showFragmentB() {
        replaceFragment(FragmentB())

    }

    fun showFragmentC() {
        replaceFragment(FragmentC())

    }

    override fun enterAnim(): Animator {
        return AnimatorInflater.loadAnimator(activity, R.animator.fragment_slide_up)
    }

    override fun exitAnim(): Animator {
        if (slideDown) {
            this.slideDown = false
            return AnimatorInflater.loadAnimator(activity, R.animator.fragment_slide_down)
        }
        return super.exitAnim()
    }

    override fun popAnim(): Animator {
        if (slideDown) {
            this.slideDown = false
            return AnimatorInflater.loadAnimator(activity, R.animator.fragment_slide_down)
        }
        return super.popAnim()
    }
}