package com.erbe.gdgfinder.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erbe.gdgfinder.R
import com.erbe.gdgfinder.databinding.AddGdgFragmentBinding
import com.google.android.material.snackbar.Snackbar

class AddGdgFragment : Fragment() {

    private val viewModel: AddGdgViewModel by lazy {
        ViewModelProvider(this).get(AddGdgViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AddGdgFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.showSnackbarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.application_submitted),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()

                viewModel.doneShowingSnackbar()
                binding.button.contentDescription = getString(R.string.submitted)
                binding.button.text = getString(R.string.done)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}