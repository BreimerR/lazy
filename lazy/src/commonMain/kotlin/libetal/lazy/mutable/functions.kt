package libetal.lazy.mutable

fun <T> mutableLazy(initializer: () -> T) = Lazy(initializer)