package dev.hinaka.pokedex.domain.common

@DslMarker
annotation class DomainDslMarker

@DomainDslMarker
internal interface DomainBuilder<T> {
    fun build(): T
}

fun <T, B : DomainBuilder<T>> build(builder: B, init: B.() -> Unit): T {
    builder.init()
    return builder.build()
}