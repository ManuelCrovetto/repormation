package com.macrosystems.repormation

import android.app.Application
import com.macrosystems.core.data.di.coreDataModule
import com.macrosystems.detail.data.di.detailDataModule
import com.macrosystems.list.data.di.listDataModule
import com.macrosystems.list.presentation.di.listViewModelModule
import com.macrosystems.presentation.di.detailViewModelModule
import org.koin.core.context.startKoin

class RepormationApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                coreDataModule,
                listViewModelModule,
                listDataModule,
                detailDataModule,
                detailViewModelModule
            )
        }
    }
}