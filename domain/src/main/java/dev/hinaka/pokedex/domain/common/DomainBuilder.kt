package dev.hinaka.pokedex.domain.common

@DslMarker
annotation class DomainDslMarker

@DomainDslMarker
abstract class DomainBuilder<T> {
    abstract fun build(): T

    protected fun <X, B : DomainBuilder<X>> initBuilder(builder: B, init: B.() -> Unit): X {
        builder.init()
        return builder.build()
    }
}