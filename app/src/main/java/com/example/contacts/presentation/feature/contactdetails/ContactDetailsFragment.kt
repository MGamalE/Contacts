package com.example.contacts.presentation.feature.contactdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.contacts.databinding.FragmentContactDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactDetails : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    private val contactDetailsViewModel:ContactDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactDetailsViewModel.retrieveContactDetails("5aba31ea9007ba0f570c92d4")

        lifecycleScope.launch {
                contactDetailsViewModel.uiState.collectLatest { state->

                    state.isLoading.let {
                        Toast.makeText(activity,""+state.isLoading,Toast.LENGTH_SHORT).show()
                    }

                    state.contactData.let {
                        Toast.makeText(activity,""+state.contactData?.contactDetailsData?.contact?.addresses,Toast.LENGTH_SHORT).show()
                    }

                    state.errorMessage.let {
                        Toast.makeText(activity,""+state.errorMessage,Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}