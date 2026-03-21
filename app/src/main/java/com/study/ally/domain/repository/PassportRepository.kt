package com.study.ally.domain.repository

import com.study.ally.domain.model.Allergen

interface PassportRepository {
    suspend fun getAllergens(): List<Allergen>
}