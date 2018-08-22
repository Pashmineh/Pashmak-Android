package app.pashmak.com.pashmak.di.module

import android.app.Application
import android.content.Context
import app.pashmak.com.pashmak.di.builder.ViewModelBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelBuilder::class])
class AppModule
{
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}