package com.dre.loyalty.features.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.databinding.FragmentOtpBinding
import javax.inject.Inject

class OtpFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: FragmentOtpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).run {
            setSupportActionBar(binding?.toolbarLayout?.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.btnResend?.setOnClickListener {
            activity?.let {
                navigator.showUserDetailForm(it)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    companion object {
        fun newInstance(): OtpFragment {
            return OtpFragment()
        }
    }
}
