/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dre.loyalty.core.platform

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dre.loyalty.AndroidApplication
import com.dre.loyalty.R.color
import com.dre.loyalty.core.di.component.ApplicationComponent
import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.networking.exception.Failure.NetworkConnection
import com.dre.loyalty.core.networking.exception.Failure.ServerError
import com.dre.loyalty.core.platform.extension.appContext
import com.dre.loyalty.core.platform.extension.viewContainer
import com.dre.loyalty.core.platform.util.enumtype.ConfirmationSheetType
import com.dre.loyalty.core.view.sheet.ConfirmationSheetModal
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun notify(@StringRes message: Int) =
            Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext, color.colorTextPrimary))
        snackBar.show()
    }

    protected fun getNetworkErrorSheet(failure: Failure): ConfirmationSheetModal? {
        return when (failure) {
            is NetworkConnection -> ConfirmationSheetModal.newInstance(ConfirmationSheetType.NO_INTERNET_CONNECTION_SHEET)
            is ServerError -> ConfirmationSheetModal.newInstance(ConfirmationSheetType.RESPONSE_ERROR_SHEET)
            else -> null
        }
    }
}
