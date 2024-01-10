package com.example.challenge.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias inflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding>(private val inflate: inflater<VB>) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        bindViewActionListeners()
        bindObserves()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun changeVisibility(vararg views: View) {
        views.forEach {view ->
            view.isVisible = !view.isVisible
        }
    }

    open fun bind(){}
    open fun bindViewActionListeners(){}
    abstract fun bindObserves()
}