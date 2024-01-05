package org.example

class CoffeeMachine(private var water: Int, private var milk: Int, private var beans: Int, private var cups: Int, private var cash: Int) {

    var state: State = State.SELECT
    private var stage : Int = 0
    private val coffeeMachineMap = mutableMapOf<String,Int>()
    var isRunning = true

    init {
        update()
    }

    private fun update(){
        coffeeMachineMap["Water"] = water
        coffeeMachineMap["Milk"] = milk
        coffeeMachineMap["Beans"] = beans
        coffeeMachineMap["Cups"] = cups
        coffeeMachineMap["Cash"] = cash
    }

    fun printCurrentState() : Unit{
        when(state){
            State.SELECT -> println("\nWrite action (buy, fill, take, remaining, exit):")
            State.FILL -> {
                if(stage == 0) println("\nWrite how many ml of water you want to add:")
                else if(stage == 1) println("Write how many ml of milk you want to add:")
                else if(stage == 2) println("Write how many grams of coffee beans you want to add:")
                else if(stage == 3) println("Write how many disposable cups you want to add:")
            }
            State.BUY -> println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            State.EXIT -> println("Shutting down.")
            else -> println()
        }
    }

    fun procedure(action: String){
        when(state){
            State.SELECT -> {
                when(action){
                    State.FILL.action -> state = State.FILL
                    State.BUY.action -> state = State.BUY
                    State.TAKE.action -> state = State.TAKE
                    State.REMAINING.action -> state = State.REMAINING
                    State.EXIT.action -> state = State.EXIT
                }
            }
            State.FILL -> fill(action, stage)
            State.BUY -> {
                buy(action)
            }
            else -> println()
        }
        if (state == State.TAKE) take()
        else if(state == State.REMAINING){
            printCoffeeMachine(coffeeMachineMap)
            state = State.SELECT
        }
        else if(state == State.EXIT) Runtime.getRuntime().exit(0)
    }

    private fun printCoffeeMachine(map: MutableMap<String,Int>) : Unit{
        println("\nThe coffee machine has:")
        println("${map["Water"]} ml of water")
        println("${map["Milk"]} ml of milk")
        println("${map["Beans"]} g of coffee beans")
        println("${map["Cups"]} disposable cups")
        println("\$${map["Cash"]} of money")
    }

    private fun buy(action: String){
        // What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:
        when(action){
            "1" -> makeCoffee(coffeeMachineMap, Coffee.ESPRESSO)
            "2" -> makeCoffee(coffeeMachineMap, Coffee.LATTE)
            "3" -> makeCoffee(coffeeMachineMap, Coffee.CAPPUCCINO)
            "back" -> println()
        }
        this.state = State.SELECT
    }

    private fun fill(input: String, stage: Int){
        when(stage){
            // Write how many ml of water you want to add:
            0 -> {
                water += input.toInt()
                this.stage++
            }
            // Write how many ml of milk you want to add:
            1 -> {
                milk += input.toInt()
                this.stage++
            }
            // Write how many grams of coffee beans you want to add:
            2 -> {
                beans += input.toInt()
                this.stage++
            }
            // Write how many disposable cups you want to add:
            3 -> {
                cups += input.toInt()
                this.stage = 0
                this.state = State.SELECT
            }
        }
        update()
    }

    private fun makeCoffee(coffeeMachineMap: MutableMap<String,Int>, coffee: Coffee) : Unit{
        if(isIngredients(coffee)){
            water -= coffee.water; milk -= coffee.milk; beans -= coffee.beans; cups--; cash += coffee.price
            update()
        }
    }

    private fun take(){
        println("\nI gave you \$$cash")
        cash = 0
        update()
        this.state = State.SELECT
    }

    private fun isIngredients(coffee: Coffee) : Boolean{
        val sb = StringBuilder()
        var isResources = true
        if((coffeeMachineMap["Water"] ?: 0) <= coffee.water){
            sb.append("Sorry, not enough water!")
            isResources = false
        }
        if((coffeeMachineMap["Beans"] ?: 0) <= coffee.beans){
            sb.append("Sorry, not enough beans!")
            isResources = false
        }
        if((coffeeMachineMap["Milk"] ?: 0) <= coffee.milk){
            sb.append("Sorry, not enough milk!")
            isResources = false
        }
        if(coffeeMachineMap["Cups"] == 0){
            sb.append("Sorry, not enough cups!")
            isResources = false
        }
        if(isResources){
            sb.append("I have enough resources, making you a coffee!")
            update()
        }
        println(sb.toString())
        return isResources
    }
}