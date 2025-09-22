package com.rizzard23.kuizu.di

import com.rizzard23.kuizu.data.QuizzesViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        QuizzesViewModel()
    }
}