package libetal.lazy.contexed

import kotlin.test.Test
import kotlin.test.assertNotEquals

class ValueRetrieverTest {

    class ClassName(val pkg: String, vararg val names: String) {
        val name
            get() = "$pkg.${names.joinToString(".")}"
    }

    class TestingClass() {

        val packageNames = listOf(
            "com.libetal.Rebo",
            "com.libetal.Exposed",
            "com.libetal.Lazy"
        )

        val String.className: ClassName by contexedLazy {
            val splat = split('.')

            ClassName(splat.subList(0, splat.size - 1).joinToString("."), splat.last())
        }

        val classNames
            get() = packageNames.map { it.className }


        val repetitiveRetrieveClassName
            get() = listOf(packageNames[0].className, packageNames[1].className, packageNames[0].className)

    }

    @Test
    fun simple() = test(TestingClass().classNames)

    @Test
    fun simpleSameContextRepetitiveRetrieve() {
        val testing = TestingClass()

        test(testing.repetitiveRetrieveClassName)
    }

    fun test(classNames: List<ClassName>) {
        var old: String? = null
        classNames.forEach {
            assertNotEquals(old, it.name)
            old = it.name
        }
    }
}