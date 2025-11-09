package org.onishkovvalery.vehicleservice.utils

import org.springframework.data.jpa.domain.Specification

class SpecificationBuilder<T> {
    val specifications = mutableListOf<Specification<T>>()

    fun like(field: String?, name: String): SpecificationBuilder<T> {
        if (field != null) {
            specifications += Specification<T> { root, _, cb ->
                cb.like(root.get(name), "%$field%")
            }
        }
        return this
    }

    fun <V> eq(field: V?, name: String): SpecificationBuilder<T> {
        if (field != null) {
            specifications += Specification<T> { root, _, cb -> cb.equal(root.get<V>(name), field) }
        }
        return this
    }

    fun build(): Specification<T> = Specification.allOf(*specifications.toTypedArray())
}
