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
import com.bowerswilkins.nestedfragmenttransitions.R
import com.bowerswilkins.nestedfragmenttransitions.childfragments.ChildFragment

abstract class ParentFragment : BaseFragment() {

    //region BaseFragment

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.let {
            return FragmentView(it.context)
        }
        return null
    }

    @IdRes
    fun getContainerId(): Int {
        return R.id.fragment_parent_container
    }

    override fun setPopping() {
        super.setPopping()
        val topFragment = getTopFragment()
        if (topFragment is ChildFragment) {
            topFragment.setSuppressing()
        }
    }

    //endregion

    //region Methods

    fun handledBackPress(): Boolean {
        Log.e(javaClass.simpleName, "handledBackPress")
        if (childFragmentManager.backStackEntryCount > 1) {
            val fragment = getTopFragment()
            if (fragment is ChildFragment) {
                fragment.setPopping()
                val secondFragmentEntry = childFragmentManager.getBackStackEntryAt(childFragmentManager.backStackEntryCount - 2)
                val secondFragment = childFragmentManager.findFragmentByTag(secondFragmentEntry.name)
                if (secondFragment is ChildFragment) {
                    secondFragment.setPopping()
                }

                this.childFragmentManager.popBackStack(fragment.getTagName(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return true
            }
        }
        return false
    }

    fun addFragment(fragment: ChildFragment) {
        fragment.setSuppressing()
        childFragmentManager.beginTransaction()
                .add(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun replaceFragment(fragment: ChildFragment) {
        childFragmentManager.beginTransaction()
                .replace(getContainerId(), fragment, fragment.getTagName())
                .addToBackStack(fragment.getTagName())
                .commit()
    }

    fun getTopFragment(): Fragment? {
        val topfragment = childFragmentManager.findFragmentById(getContainerId())
        Log.e(javaClass.simpleName, "topFragment = " + (topfragment?.toString() ?: "null"))
        return topfragment
    }

    //endregion

}