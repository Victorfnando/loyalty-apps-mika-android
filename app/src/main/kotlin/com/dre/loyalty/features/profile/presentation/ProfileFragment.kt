/*
 * Created by Andreas Oen on 12/31/20 6:31 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/31/20 6:31 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.databinding.FragmentProfileBinding
import com.dre.loyalty.features.profile.presentation.entity.Menu
import com.dre.loyalty.features.profile.presentation.item.LogoutMenuItem
import com.dre.loyalty.features.profile.presentation.item.ProfileMenuItem
import com.dre.loyalty.features.profile.presentation.item.ProfileMenuSection
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class ProfileFragment : BaseFragment() {

    private var binding: FragmentProfileBinding? = null

    private val menuSectionAdapter: ItemAdapter<ProfileMenuSection> by lazy {
        ItemAdapter<ProfileMenuSection>()
    }

    private val accountItemSection: ItemAdapter<ProfileMenuItem> by lazy {
        ItemAdapter<ProfileMenuItem>()
    }

    private val aboutItemSection: ItemAdapter<ProfileMenuItem> by lazy {
        ItemAdapter<ProfileMenuItem>()
    }

    private val logoutMenuAdapter: ItemAdapter<LogoutMenuItem> by lazy {
        ItemAdapter<LogoutMenuItem>()
    }

    private lateinit var vm: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateChangePassword, ::navigateChangePasswordScreen)
            observe(navigateChangeProfile, ::navigateChangeProfileScreen)
            observe(navigateContact, ::navigateContactScreen)
            observe(navigateFaq, ::navigateFaqScreen)
            observe(navigateTnc, ::navigateTnCScreen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindList()
    }

    private fun bindList() {
        binding?.rvMenu?.run {
            addItemDecoration(
                VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL).also {
                    ContextCompat.getDrawable(requireContext(), R.drawable.normal_divider)?.let { divider ->
                        it.setDrawable(divider)
                    }
                }
            )
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = FastAdapter.with(listOf(menuSectionAdapter, logoutMenuAdapter))
        }
        populateAccountSection()
        populateAboutSection()
        populateMenuSection()
        logoutMenuAdapter.add(LogoutMenuItem {
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
        })
    }

    private fun populateAccountSection() {
        accountItemSection.add(
            ProfileMenuItem(Menu.CHANGE_PROFILE) {
                vm.handleChangeProfileMenuClicked()
            },
            ProfileMenuItem(Menu.CHANGE_PASSWORD) {
                vm.handleChangePasswordMenuClicked()
            },
        )
    }

    private fun populateAboutSection() {
        aboutItemSection.add(
            ProfileMenuItem(Menu.FAQ) {
                vm.handleFaqMenuClicked()
            },
            ProfileMenuItem(Menu.CONTACT) {
                vm.handleContactMenuClicked()
            },
            ProfileMenuItem(Menu.TNC) {
                vm.handleTnCMenuClicked()
            }
        )
    }

    private fun populateMenuSection() {
        menuSectionAdapter.add(
            ProfileMenuSection(
                getString(R.string.profile_screen_label_account),
                listOf(accountItemSection)
            ).also {
                it.dividerItemDecoration = VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            },
            ProfileMenuSection(
                getString(R.string.profile_screen_label_about),
                listOf(aboutItemSection)
            ).also {
                it.dividerItemDecoration = VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            }
        )
    }

    private fun navigateChangePasswordScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), "change password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateChangeProfileScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), "change profile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateFaqScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), "faq", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateContactScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), "contact", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateTnCScreen(event: Event<Boolean>?) {
        event?.getIfNotHandled()?.let {
            Toast.makeText(requireContext(), "tnc", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
