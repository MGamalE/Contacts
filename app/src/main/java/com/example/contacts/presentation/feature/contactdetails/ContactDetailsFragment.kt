package com.example.contacts.presentation.feature.contactdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactDetailsBinding
import com.example.contacts.databinding.ToolbarBinding
import com.example.contacts.entity.contactdetails.ContactAddress
import com.example.contacts.entity.contactdetails.ContactDetailsResponse
import com.example.contacts.entity.contactdetails.ContactEmails
import com.example.contacts.entity.contactdetails.ContactPhones
import com.example.contacts.presentation.core.ErrorType
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactDetailsFragment : Fragment() {

    private var toolbarBinding: ToolbarBinding? = null
    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    private val contactDetailsViewModel: ContactDetailsViewModel by viewModels()

    private val args: ContactDetailsFragmentArgs by navArgs()
    private var contactId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactId = args.contactId
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

        initializeToolbar()

        fireSwipeRefreshing()

        retrieveContactDetails(contactId)

        initializeUiStateObserving()

    }

    /**
     * Initialize toolbar of contact details screen
     */
    private fun initializeToolbar() {
        toolbarBinding = binding.toolbar
    }

    private fun initializeUiStateObserving() {
        lifecycleScope.launch {
            contactDetailsViewModel.uiState.collect { state ->
                /**
                 * Listen to retrieved loading state
                 */
                state.isLoading.let { loading -> binding.SwipeRefreshLayout.isRefreshing = loading }

                /**
                 * Listen to retrieved success contact data
                 */
                state.contactData?.let { response ->
                    drawContactViews(response)
                }

                /**
                 * Listen to retrieved error message
                 */
                state.errorMessage?.let { error ->
                    displayErrorMessage(error)
                }
            }
        }

    }


    /**
     * Setup swipe refreshing
     */
    private fun fireSwipeRefreshing() {
        binding.SwipeRefreshLayout.setOnRefreshListener { retrieveContactDetails(contactId) }
    }


    /**
     * Request the details of contact
     *
     * @param contactId The id of requested contact
     */
    private fun retrieveContactDetails(contactId: String) {
        contactDetailsViewModel.retrieveContactDetails(contactId)
    }

    /**
     * Assign each value to specified view
     *
     * @param response the data class that hold contact details
     */
    private fun drawContactViews(response: ContactDetailsResponse) {

        drawContactPhoto(response.contactDetailsData.contact.phoneUrl)

        drawContactFullName(
            response.contactDetailsData.contact.firstName,
            response.contactDetailsData.contact.lastName
        )

        drawContactTitle(response.contactDetailsData.contact.title)

        drawContactPhone(response.contactDetailsData.contact.phones)

        drawContactCompany(response.contactDetailsData.contact.companyName)

        drawContactEmail(response.contactDetailsData.contact.emails)

        drawContactAddress(response.contactDetailsData.contact.addresses)

        drawToolbarTitle(
            response.contactDetailsData.contact.firstName,
            response.contactDetailsData.contact.lastName
        )

    }

    private fun drawToolbarTitle(firstName: String?, lastName: String?) {
        toolbarBinding?.topAppBar?.title = "$firstName $lastName"
    }

    private fun drawContactAddress(addresses: List<ContactAddress>?) {
        if (!addresses.isNullOrEmpty()) binding.tvContactAddress.text = addresses[0].address
    }

    private fun drawContactEmail(emails: List<ContactEmails>?) {
        if (!emails.isNullOrEmpty()) binding.tvContactMail.text = emails[0].value
    }

    private fun drawContactCompany(companyName: String?) {
        binding.tvContactCompany.text = companyName
    }

    private fun drawContactPhone(phones: List<ContactPhones>?) {
        if (!phones.isNullOrEmpty()) {
            binding.tvContactPhone.text = phones[0].value
            binding.tvContactPhoneType.text = phones[0].type
        }
    }

    private fun drawContactTitle(title: String?) {
        binding.tvContactTitle.text = title
    }

    private fun drawContactFullName(firstName: String?, lastName: String?) {
        binding.tvContactName.text = "$firstName $lastName"
    }

    private fun drawContactPhoto(phoneUrl: String?) {
        binding.imgContactPhoto.load(phoneUrl)
    }


    /**
     * Assign each error to specified message
     *
     * @param error Enum class hold api request errors
     */
    private fun displayErrorMessage(error: ErrorType) {
        when (error) {
            ErrorType.SERVER_ERROR -> showMessage(getString(R.string.error_server))
            ErrorType.UNAUTHORIZED_ERROR -> showMessage(getString(R.string.error_un_authorized))
            ErrorType.NOT_FOUND_ERROR -> showMessage(getString(R.string.error_not_found))
            ErrorType.FORBIDDEN_ERROR -> showMessage(getString(R.string.error_permission))
            ErrorType.CONNECTION_ERROR -> showMessage(getString(R.string.error_connection))
            ErrorType.UNEXPECTED_ERROR -> showMessage(getString(R.string.error_generic))
            ErrorType.GENERIC_ERROR -> showMessage(getString(R.string.error_generic))
        }
    }

    /**
     * Display snackbar error
     *
     * @param message The error body that will be displayed
     */
    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        toolbarBinding = null
    }
}