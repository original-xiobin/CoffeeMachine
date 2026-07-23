# CoffeeMachine

A Kotlin console application that simulates a coffee machine.  
The program lets you buy coffee, refill resources, take money from the machine, check the remaining inventory, and exit the simulation.

## Features

- Buy espresso, latte, or cappuccino
- Refill water, milk, coffee beans, and disposable cups
- Take the cash stored in the machine
- View the current machine state
- Handle resource checks before making coffee

## Tech Stack

- Kotlin
- JVM 21
- Gradle Kotlin DSL

## How It Works

The machine starts with preset resources and cash, then waits for simple text commands in a terminal menu:

- `buy`
- `fill`
- `take`
- `remaining`
- `exit`

When you choose a coffee type, the machine checks whether enough ingredients are available before preparing it.

## Project Structure

- `src/main/kotlin/Main.kt` — application entry point
- `src/main/kotlin/CoffeeMachine.kt` — core machine logic
- `src/main/kotlin/Coffee.kt` — coffee recipes and prices
- `src/main/kotlin/State.kt` — menu states

## Getting Started

Open the project in IntelliJ IDEA and run `Main.kt`.

## Notes

This project was built as a Hyperskill-project.
