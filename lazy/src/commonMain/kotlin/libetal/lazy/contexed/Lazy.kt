package libetal.lazy.contexed

import kotlin.reflect.KProperty

class Lazy<T, R>(var initializer: T.() -> R) {

    private var values = mutableMapOf<Int, R>()

    operator fun getValue(receiver: T, property: KProperty<*>): R {

        val hash = receiver.hashCode()

        return values[hash] ?: run {

            val newValue = initializer(receiver)

            values[hash] = newValue

            newValue

        }

    }

}
