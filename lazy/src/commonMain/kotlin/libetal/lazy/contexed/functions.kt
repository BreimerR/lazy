package libetal.lazy.contexed

fun <T, R> contexedLazy(func: T.() -> R) = Lazy(func)