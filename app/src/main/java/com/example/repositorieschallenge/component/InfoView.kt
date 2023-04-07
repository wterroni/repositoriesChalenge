package com.example.repositorieschallenge.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.repositorieschallenge.R
import com.example.repositorieschallenge.databinding.InfoViewBinding

class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.InfoViewTheme,
    defStyleRes: Int = R.style.Theme_RepositoriesChallenge
): ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding = InfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    @DrawableRes
    private var icon: Int? = null
        set(value) {
            field = value
            value?.let {
                binding.infoImage.setImageResource(value)
                showIcon()
            } ?: hideIcon()
        }

    private var title: String? = null
        set(value) {
            field = value
            value?.let {
                binding.titleTextView.text = value
                showTitle()
            } ?: hideTitle()
        }

    private var description: String? = null
        set(value) {
            field = value
            value?.let {
                binding.descriptionTextView.text = value
                showDescription()
            } ?: hideDescription()
        }

    private var onClickIcon: () -> Unit = {}

    fun setState(state: InfoViewState) {
        icon = state.iconRes
        title = state.title
        description = state.description

        onClickIcon = state.iconAction
    }

    init {
        setupListeners()
    }

    private fun setupListeners() {
        binding.infoImage.setOnClickListener {
            onClickIcon()
        }
    }

    private fun showIcon() {
        binding.infoImage.isVisible = true
    }

    private fun hideIcon() {
        binding.infoImage.isGone = true
    }

    private fun showTitle() {
        binding.titleTextView.isVisible = true
    }

    private fun hideTitle() {
        binding.titleTextView.isGone = true
    }

    private fun showDescription() {
        binding.descriptionTextView.isVisible = true
    }

    private fun hideDescription() {
        binding.descriptionTextView.isGone = true
    }
}