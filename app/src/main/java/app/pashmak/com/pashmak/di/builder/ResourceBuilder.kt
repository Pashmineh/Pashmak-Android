package app.pashmak.com.pashmak.di.builder

import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import app.pashmak.com.pashmak.util.providers.BaseViewProvider
import app.pashmak.com.pashmak.util.providers.ResourceProvider
import app.pashmak.com.pashmak.util.providers.ViewProvider
import dagger.Binds
import dagger.Module

@Module
interface ResourceBuilder
{
    @Binds
    fun bindResourceProvider(resourceProvider: ResourceProvider): BaseResourceProvider

    @Binds
    fun bindViewProvider(viewProvider: ViewProvider): BaseViewProvider
}