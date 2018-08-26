package app.pashmak.com.pashmak.di.component

import android.app.Application
import app.pashmak.com.pashmak.app.PashmakApplication
import app.pashmak.com.pashmak.di.builder.ActivityBuilder
import app.pashmak.com.pashmak.di.builder.RepositoryBuilder
import app.pashmak.com.pashmak.di.builder.ViewModelBuilder
import app.pashmak.com.pashmak.di.module.AppModule
import app.pashmak.com.pashmak.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AppModule::class,
            ActivityBuilder::class,
            NetworkModule::class,
            ViewModelBuilder::class,
            RepositoryBuilder::class,
            AndroidInjectionModule::class
        ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: PashmakApplication)
}