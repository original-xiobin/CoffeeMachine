package org.example

enum class Coffee(val water: Int, val milk: Int, val beans: Int, val price: Int) {
    ESPRESSO(
        250,
        0,
        16,
        4
    ),
    LATTE(
        350,
        75,
        20,
        7
    ),
    CAPPUCCINO(
        200,
        100,
        12,
        6
    )
}
