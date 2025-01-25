package com.manoj.noteapp.utils.ui

sealed class OrderType {

    object Ascending : OrderType()
    object Descending : OrderType()
}