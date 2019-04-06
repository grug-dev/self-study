package com.kkpa.tutorialkotlin.generics

import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class ListRequest<T> {

    @NotNull
    @Size(min = 1)
    @Valid
    var list: List<T>? = null

    override fun toString(): String {
        return "ListRequest{" +
                "list=" + list +
                '}'.toString()
    }
}