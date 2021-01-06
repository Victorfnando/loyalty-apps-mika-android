/*
 * Created by Andreas Oen on 12/30/20 8:15 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 12/30/20 8:15 PM
 * github: https://github.com/oandrz
 */

package com.dre.loyalty.features.home.presentation

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dre.loyalty.R
import com.dre.loyalty.core.extension.observe
import com.dre.loyalty.core.extension.viewModel
import com.dre.loyalty.core.functional.Event
import com.dre.loyalty.core.navigation.Navigator
import com.dre.loyalty.core.platform.BaseFragment
import com.dre.loyalty.core.view.HorizontalSpaceDecoration
import com.dre.loyalty.core.view.VerticalDividerDecoration
import com.dre.loyalty.databinding.FragmentHomeBinding
import com.dre.loyalty.features.news.presentation.entity.News
import com.dre.loyalty.features.home.presentation.view.HomeSection
import com.dre.loyalty.features.cashback.presentation.item.CashBackItem
import com.dre.loyalty.features.news.presentation.view.NewsItem
import com.dre.loyalty.features.cashback.presentation.item.UploadInvoiceItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import javax.inject.Inject

@RuntimePermissions
class HomeFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var vm: HomeViewModel

    private var binding: FragmentHomeBinding? = null

    private val homeSection: ItemAdapter<HomeSection> by lazy {
        ItemAdapter<HomeSection>()
    }

    private val uploadInvoiceItem: ItemAdapter<UploadInvoiceItem> by lazy {
        ItemAdapter<UploadInvoiceItem>()
    }

    private val cashBackItem: ItemAdapter<CashBackItem> by lazy {
        ItemAdapter<CashBackItem>()
    }

    private val newsItem: ItemAdapter<NewsItem> by lazy {
        ItemAdapter<NewsItem>()
    }

    private val easyImage: EasyImage by lazy {
        EasyImage.Builder(requireContext())
            .setCopyImagesToPublicGalleryFolder(true)
            .setFolderName("Loyalty App")
            .allowMultiple(true)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = viewModel(viewModelFactory) {
            observe(navigateCashBackList, ::showCashBackListScreen)
            observe(navigateNewsList, ::showNewsListScreen)
            observe(navigateNewsDetail, ::showNewsDetailScreen)
            observe(navigateToCamera, ::showCameraWithPermissionCheck)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        cashBackItem.add(
//            listOf(
//                CashBackItem(CashBack("10", 10000L, "12 Desember 2020")),
//                CashBackItem(CashBack("20", 20000L, "20 Desember 2020"))
//            )
//        )

        uploadInvoiceItem.add(
            UploadInvoiceItem {
                vm.handleUploadInvoiceClicked()
            }
        )

        newsItem.add(
            listOf(
                NewsItem(News("12 Desember 2020", "Rumah Imunisasi Mitra Keluarga Bekasi", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", "")),
                NewsItem(News("20 Desember 2020", "Rumah Imunisasi Mitra Keluarga Jakarta", "Rumah Imunisasi Mitra Keluarga Bekasi Rumah Imunisasi Mitra Keluarga Bekasi", ""))
            )
        )
        homeSection.add(listOf(
            HomeSection(
                "Cashback diterima",
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false),
                listOf(cashBackItem, uploadInvoiceItem)
            ).also {
                it.itemClickListener = {
                    vm.handleCashBackItemClicked()
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllCashBackClicked()
                }
                it.dividerItemDecoration = VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL)
            },
            HomeSection(
                "Informasi",
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false),
                listOf(newsItem)
            ).also {
                it.itemClickListener = {
                    vm.handleNewsItemClicked()
                }
                it.seeAllClickListener = {
                    vm.handleSeeAllNewsClicked()
                }
                it.snapHelper = LinearSnapHelper()
                it.dividerItemDecoration = HorizontalSpaceDecoration(resources.getDimensionPixelSize(
                    R.dimen.space_16dp))
            }
        ))
        binding?.rvContent?.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(
                VerticalDividerDecoration(requireContext(), RecyclerView.VERTICAL).also {
                    ContextCompat.getDrawable(requireContext(), R.drawable.normal_divider)?.let { divider ->
                        it.setDrawable(divider)
                    }
                }
            )
            adapter = FastAdapter.with(homeSection)
        }
    }

    override fun onDetach() {
        binding = null
        super.onDetach()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                data?.let {
                    handleCrop(it)
                }
            } else {
                handleImagePicker(requestCode, resultCode, data)
            }
        }
    }

    private fun handleImagePicker(requestCode: Int, resultCode: Int, data: Intent?) {
        easyImage.handleActivityResult(requestCode, resultCode, data, requireActivity(), object : DefaultCallback() {
            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                val sourceUri = Uri.fromFile(imageFiles.first().file)
                val crop = UCrop.of(sourceUri, sourceUri)
                val option = UCrop.Options()
                option.run {
                    setAllowedGestures(UCropActivity.ALL, UCropActivity.ALL, UCropActivity.ALL)
                    withAspectRatio(3f, 4f)
                    setShowCropFrame(false)
                    setShowCropGrid(false)
                    setCompressionQuality(100)
                }
                crop.withOptions(option).start(requireContext(), this@HomeFragment)
            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                super.onImagePickerError(error, source)
                error.printStackTrace()
            }
        })
    }

    private fun handleCrop(data: Intent) {
        UCrop.getOutput(data)?.let {
            navigator.showUploadInvoice(requireContext(), it)
        }
    }

    private fun showCashBackListScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showCashBackList(requireContext())
        }
    }

    private fun showNewsListScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showNewsList(requireContext())
        }
    }

    private fun showNewsDetailScreen(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            navigator.showNewsDetail(requireContext())
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showCamera(event: Event<String>?) {
        event?.getIfNotHandled()?.let {
            easyImage.openCameraForImage(this)
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
