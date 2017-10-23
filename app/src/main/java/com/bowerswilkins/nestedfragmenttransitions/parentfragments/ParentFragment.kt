package com.bowerswilkins.nestedfragmenttransitions.parentfragments

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment

open class ParentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_parent, container, false)
    }

    @IdRes
    fun getContainerId(): Int {
        return R.id.fragment_parent_container
    }

    fun handledBackPress(): Boolean {
        if (childFragmentManager.backStackEntryCount > 1) {
            val fragment = childFragmentManager.findFragmentById(getContainerId())
            if (fragment is ChildFragment) {
                this.childFragmentManager.popBackStack(fragment.getTagName(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return true
            }
        }
        return false
    }

    fun addFragment(fragment: ChildFragment) {
        childFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fragment_nothing,
                                     R.animator.fragment_nothing,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)
                .add(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun replaceFragment(fragment: ChildFragment) {
        childFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fragment_enter,
                                     R.animator.fragment_exit,
                                     R.animator.fragment_push,
                                     R.animator.fragment_pop)
                .replace(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }


}