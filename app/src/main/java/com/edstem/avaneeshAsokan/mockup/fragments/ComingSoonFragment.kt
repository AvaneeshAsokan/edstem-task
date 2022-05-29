package com.edstem.avaneeshAsokan.mockup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edstem.mockup.databinding.FragmentComingSoonBinding

/**
 * A catch all fragment to show as a placeholder for unimplemented screens
 */
class ComingSoonFragment: Fragment() {

    private lateinit var binding: FragmentComingSoonBinding

    companion object {
        private const val FRAGMENT_TITLE = "title_extra"

        fun newInstance(title: String): ComingSoonFragment {
            val args = Bundle()
            args.putString(FRAGMENT_TITLE, title)
            val fragment = ComingSoonFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComingSoonBinding.inflate(inflater, container, false)
        val title = requireArguments().getString(FRAGMENT_TITLE)
        binding.screenTitle.text = title
        return binding.root
    }
}