package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bowerswilkins.nestedfragmenttransitions.BaseFragment
import com.bowerswilkins.nestedfragmenttransitions.GlobalClickListener
import com.bowerswilkins.nestedfragmenttransitions.R
import kotlinx.android.synthetic.main.fragment_child.*

abstract class ChildFragment : BaseFragment() {

    //region BaseFragment
    protected var duration: Long = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        duration = resources.getInteger(R.integer.duration).toLong()
        container?.let {
            return ChildFragmentView(it.context)
        }
        return null
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_child_button.text = getButtonText()
        fragment_child_button.setOnClickListener(getButtonAction())
        fragment_child_header.text = getTextViewText()
    }

    //endregion

    //region Abstract Methods

    abstract fun getTextViewText(): String

    abstract fun getButtonText(): String

    abstract fun getButtonAction(): GlobalClickListener

    //endregion

}