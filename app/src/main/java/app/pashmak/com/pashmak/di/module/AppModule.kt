package app.pashmak.com.pashmak.di.module

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import app.pashmak.com.pashmak.di.builder.ViewModelBuilder
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import app.pashmak.com.pashmak.util.providers.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule
{
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideBluetoothAdapter(context: Context): BluetoothAdapter? {
        return (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }
}