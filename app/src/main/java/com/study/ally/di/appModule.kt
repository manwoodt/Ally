package com.study.ally.di

import com.study.ally.data.datastore.DataStoreManager
import com.study.ally.presentation.screens.diary.DiaryViewModel
import com.study.ally.presentation.screens.passport.PassportViewModel
import com.study.ally.presentation.screens.symptoms.SymptomsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

single {
    DataStoreManager(get())
}

    viewModel { PassportViewModel(get()) }
    viewModel { DiaryViewModel(get()) }
    viewModel { SymptomsViewModel(get()) }
}