package com.devamsba.managebudget.common.infra

sealed class Either<out R, out L> {
    data class Left<out R, out L>(val a: L) : Either<R, L>()
    data class Right<out R, out L>(val b: R) : Either<R, L>()

}

fun <E> E.left() = Either.Left<Nothing, E>(this)
fun <T> T.right() = Either.Right<T, Nothing>(this)