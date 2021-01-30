package com.dre.loyalty.features.hospital.domain.usecase

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.interactor.UseCase
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.features.hospital.domain.HospitalListRepositoryContract
import javax.inject.Inject

class GetHospitalListUseCase @Inject constructor(
    private val repository: HospitalListRepositoryContract
) : UseCase<List<Hospital>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Hospital>> {
        return repository.getHospitalList()
    }
}