package com.jocmp.buoyapp

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.jocmp.buoy.networking.UserAgentInterceptor
import com.jocmp.buoyapp.preferences.AppPreferences
import okhttp3.OkHttpClient
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class MainApplication : Application(), SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            workManagerFactory()
            setupCommonModules()
        }

        if (get<AppPreferences>().accountID.isSet()) {
            loadAccountModules()
        }
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(
                    OkHttpNetworkFetcherFactory(callFactory = {
                        OkHttpClient.Builder()
                            .addInterceptor(UserAgentInterceptor())
                            .build()
                    })
                )
            }
            .build()
    }
}
