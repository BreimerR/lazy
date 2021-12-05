package libetal.lazy.mutable

import kotlin.reflect.KProperty

class Lazy<T>(initializer: (() -> T)) {

    private var _initializer: (() -> T)? = initializer

    private var value: T? = null
        get() = _initializer?.let {
            field = it()
            field
        } ?: field


    @Suppress("UNCHECKED_CAST")
    operator fun getValue(receiver: Any, property: KProperty<*>): T {
        _initializer?.let {
            value = it()
            _initializer = null
        }

        return value as T
    }

    operator fun setValue(receiver: Any, property: KProperty<*>, value: T) {
        _initializer?.let {
            _initializer = null
        }
        this.value = value
    }

}