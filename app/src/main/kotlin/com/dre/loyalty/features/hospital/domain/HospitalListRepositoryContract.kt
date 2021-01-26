package com.dre.loyalty.features.hospital.domain

import com.dre.loyalty.core.exception.Failure
import com.dre.loyalty.core.functional.Either
import com.dre.loyalty.core.model.Hospital
import com.dre.loyalty.features.hospital.domain.usecase.GetHospitalListUseCase.Param

interface HospitalListRepositoryContract {
    fun getHospitalList(param: Param): Either<Failure, List<Hospital>>
}
