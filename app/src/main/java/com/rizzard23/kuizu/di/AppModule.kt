package com.rizzard23.kuizu.di

import com.rizzard23.kuizu.data.AddQuizDialogViewModel
import com.rizzard23.kuizu.data.QuizzesViewModel
import com.rizzard23.kuizu.data.repository.QuizRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        QuizzesViewModel(get())
    }
    single {
        QuizRepository()
    }
    viewModel {
        AddQuizDialogViewModel()
    }
}