package kz.lab4.presentation.di

import kz.lab4.data.DB
import kz.lab4.data.dao.TaskDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { DB.getDatabase(androidContext()) }
    single { provideItemFavoriteDao(get()) }
}

fun provideItemFavoriteDao(db: DB): TaskDao = db.getTaskDao()