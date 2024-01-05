package org.example
import java.util.*

val sc = Scanner(System.`in`)

fun main() {
    val coffeeMachine = CoffeeMachine(400,540,120,9,550)
    while(coffeeMachine.isRunning){
        coffeeMachine.printCurrentState()
        coffeeMachine.procedure(sc.next())
    }
}

