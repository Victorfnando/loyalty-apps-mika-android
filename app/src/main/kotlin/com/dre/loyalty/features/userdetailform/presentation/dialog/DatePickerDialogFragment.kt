/*
 * Created by Andreas Oen on 12/28/20 7:26 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/28/20 7:10 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.userdetailform.presentation.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var listener: ((String) -> Unit)? = null

    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedDate = arguments?.getString(SELECTED_DATE_KEY)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        selectedDate?.let {
            val format = SimpleDateFormat(DATE_FORMAT, Locale.US)
            val date = format.parse(it)
            if (date != null) {
                c.time = date
            }
        }
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance().also { it.set(year, month, dayOfMonth) }
        val format = SimpleDateFormat(DATE_FORMAT, Locale.US)
        listener?.invoke(format.format(c.time))
    }
    
    companion object {
        const val TAG = "DatePickerDialogFragment"
        private const val DATE_FORMAT = "dd-MM-yyyy"
        private const val SELECTED_DATE_KEY = "SELECTED_DATE_KEY"

        fun newInstance(selectedDate: String? = null): DatePickerDialogFragment {
            val args = Bundle()
            args.putString(SELECTED_DATE_KEY, selectedDate)
            val fragment = DatePickerDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
