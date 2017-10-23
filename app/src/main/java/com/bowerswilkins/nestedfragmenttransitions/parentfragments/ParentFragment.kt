package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bowerswilkins.nestedfragmenttransitions.BaseFragment
import com.bowerswilkins.nestedfragmenttransitions.MainActivity
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment

abstract class ParentFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            if (activity is MainActivity) {
                if ((activity as MainActivity).getTopFragment() == this) {
                    isConfigChange = true
                }
            }
        }
        container?.let {
            return FragmentView(it.context)
        }
        return null
    }

    @IdRes
    fun getContainerId(): Int {
        return R.id.fragment_parent_container
    }

    fun handledBackPress(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            val fragment = getTopFragment()
            if (fragment is ChildFragment) {
                fragment.isPopping = true
                val secondFragmentEntry = childFragmentManager.getBackStackEntryAt(childFragmentManager.backStackEntryCount - 2)
                val secondFragment = childFragmentManager.findFragmentByTag(secondFragmentEntry.name)
                if (secondFragment is ChildFragment) {
                    secondFragment.isPopping = true
                }

                this.childFragmentManager.popBackStack(fragment.getTagName(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return true
            }
        }
        return false
    }

    fun addFragment(fragment: ChildFragment) {
        fragment.suppressEnter = true
        childFragmentManager.beginTransaction()
                /*  .setCustomAnimations(R.animator.fragment_nothing,
                                       R.animator.fragment_nothing,
                                       R.animator.fragment_push,
                                       R.animator.fragment_pop)*/
                .add(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun replaceFragment(fragment: ChildFragment) {
        childFragmentManager.beginTransaction()
                /*.setCustomAnimations(R.animator.fragment_enter,
                                     R.animator.fragment_exit,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)*/
                .replace(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun getTopFragment(): Fragment? {
        val topfragment = childFragmentManager.findFragmentById(getContainerId())
        Log.e(javaClass.simpleName, "topFragment = " + (topfragment?.toString() ?: "null"))
        return topfragment
    }
}