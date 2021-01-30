package com.dre.loyalty.features.hospital.domain

import com.dre.loyalty.core.networking.exception.Failure
import com.dre.loyalty.core.platform.functional.Either
import com.dre.loyalty.core.model.Hospital

interface HospitalListRepositoryContract {
    fun getHospitalList(): Either<Failure, List<Hospital>>
}
