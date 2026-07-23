package org.xiobin

enum class State(val action: String){
    SELECT("select"),
    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit")
}