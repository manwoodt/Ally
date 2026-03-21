package com.study.ally.data.repositoryImpl

import com.study.ally.domain.model.Allergen
import com.study.ally.domain.repository.PassportRepository

class PassportRepositoryImpl : PassportRepository {

    override suspend fun getAllergens(): List<Allergen> {
        return listOf(
            Allergen(1, "Арахис", true),
            Allergen(2, "Пыльца", true),
            Allergen(3, "Кошки", false),
            Allergen(4, "Молоко", true)
        )
    }
}