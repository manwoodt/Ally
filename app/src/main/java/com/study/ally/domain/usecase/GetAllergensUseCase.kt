package com.study.ally.domain.usecase

import com.study.ally.domain.model.Allergen
import com.study.ally.domain.repository.PassportRepository

class GetAllergensUseCase(
    private val repository: PassportRepository
) {
    suspend operator fun invoke(): List<Allergen> {
        return repository.getAllergens()
    }
}