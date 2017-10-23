package com.bowerswilkins.nestedfragmenttransitions.childfragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_child.*

abstract class ChildFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.let {
            return FragmentView(container.context)
        }
        return null
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_child_button.text = getButtonText()
        fragment_child_button.setOnClickListener(getButtonAction())
        fragment_child_header.text = getTextViewText()

    }

    abstract fun getTagName(): String
    abstract fun getTextViewText(): String
    abstract fun getButtonText(): String
    abstract fun getButtonAction(): View.OnClickListener


}