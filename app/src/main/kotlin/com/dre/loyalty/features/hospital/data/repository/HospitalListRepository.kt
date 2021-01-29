package com.dre.loyalty.features.hospital.data.repository

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.extension.request
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.core.platform.NetworkHandler
import com.dre.loyalty.features.hospital.data.entity.mapper.HospitalListResponseMapper
import com.dre.loyalty.features.hospital.data.repository.datasource.HospitalListCloudDataSourceContract
import com.dre.loyalty.features.hospital.domain.HospitalListRepositoryContract
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param
import javax.inject.Inject

class HospitalListRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val cloudDataSource: HospitalListCloudDataSourceContract,
    private val mapper: HospitalListResponseMapper
) : HospitalListRepositoryContract {
    override fun getHospitalList(param: Param): Either<Failure, List<Hospital>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                cloudDataSource.getHospitalList(param).request {
                    mapper.transform(it)
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}