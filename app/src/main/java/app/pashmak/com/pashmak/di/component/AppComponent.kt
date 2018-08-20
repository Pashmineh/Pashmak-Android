package app.pashmak.com.pashmak.di.component

import android.app.Application
import app.pashmak.com.pashmak.app.PashmakApplication
import app.pashmak.com.pashmak.di.builder.ActivityBuilder
import app.pashmak.com.pashmak.di.module.AppModule
import dagger.BindsInstance
import dagger.Component


@Component(
        modules = [
            AppModule::class,
            ActivityBuilder::class]
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