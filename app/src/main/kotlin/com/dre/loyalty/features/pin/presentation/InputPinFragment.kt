package com.dre.loyalty.features.pin.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentInputPinBinding
import javax.inject.Inject

class InputPinFragment : BaseFragment() {

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            vm.handleTextChanged(s.toString())
        }
    }

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentInputPinBinding? = null
    private lateinit var vm: InputPinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateToHome) {
                navigator.showHome(requireContext())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputPinBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        binding?.tvForgotpinLabel?.text = HtmlCompat.fromHtml(
           getString(R.string.pin_screen_label_forgotpin),
           HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        bindInputPin()
        binding?.tvForgotpinLabel?.setOnClickListener {
            navigator.showResetPin(requireContext())
        }
    }

    override fun onDetach() {
        binding?.etPin?.removeTextChangedListener(textWatcher)
        binding = null
        super.onDetach()
    }

    private fun bindToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun bindInputPin() {
        binding?.etPin?.run {
            requestFocus()
            addTextChangedListener(textWatcher)
        }
    }

    companion object {
        fun newInstance(): InputPinFragment {
            return InputPinFragment()
        }
    }
}
