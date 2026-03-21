package com.study.ally.di

import com.study.ally.data.repositoryImpl.PassportRepositoryImpl
import com.study.ally.domain.repository.PassportRepository
import com.study.ally.domain.usecase.GetAllergensUseCase
import com.study.ally.presentation.screens.diary.DiaryViewModel
import com.study.ally.presentation.screens.passport.PassportViewModel
import com.study.ally.presentation.screens.symptoms.SymptomsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<PassportRepository> { PassportRepositoryImpl() }

    factory { GetAllergensUseCase(repository = get()) }

    viewModel {
        PassportViewModel(
            getAllergensUseCase = get()
        )
    }

    viewModel { DiaryViewModel() }
    viewModel { SymptomsViewModel() }
}