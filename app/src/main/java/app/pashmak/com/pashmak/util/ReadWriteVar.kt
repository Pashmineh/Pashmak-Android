package app.pashmak.com.pashmak.util

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ReadWriteVar<T>(private val initializer: () -> T): ReadWriteProperty<Any?, T?>
{
    var value: T? = null

    override fun getValue(thisRef: kotlin.Any?, property: kotlin.reflect.KProperty<*>): T? {
        return  if(value == null) return initializer.invoke() else value }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
    }
}