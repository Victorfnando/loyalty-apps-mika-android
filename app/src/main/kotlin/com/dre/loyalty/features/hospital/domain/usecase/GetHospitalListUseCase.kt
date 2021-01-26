package com.dre.loyalty.features.hospital.domain.usecase

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.features.hospital.domain.HospitalListRepositoryContract
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class GetHospitalListUseCase @Inject constructor(
    private val repository: HospitalListRepositoryContract
) : UseCase<List<Hospital>, Param>() {

    override suspend fun run(params: Param): Either<Failure, List<Hospital>> {
        return repository.getHospitalList(params)
    }

    data class Param(
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("token")
        val token: String
    )
}