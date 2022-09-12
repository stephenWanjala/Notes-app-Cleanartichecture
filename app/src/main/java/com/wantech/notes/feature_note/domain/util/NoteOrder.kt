package com.wantech.notes.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Tittle(orderType: OrderType):NoteOrder(orderType)
    class Date(orderType: OrderType):NoteOrder(orderType)
    class Color (orderType: OrderType):NoteOrder(orderType)

    fun copy(orderType: OrderType) :NoteOrder =
        when(this) {
            is Tittle ->Tittle(orderType)
            is Color -> Color(orderType)
            is Date -> Date(orderType)
        }

}