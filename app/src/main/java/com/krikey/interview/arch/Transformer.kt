package com.krikey.interview.arch

interface Transformer<I, O> {

    fun transform(input: List<I>?): List<O> = input.let { inputList ->
        arrayListOf<O>().apply {
            inputList?.forEach { this.add(transform(it)) }
        }
    }

    fun transform(input: I?): O

}