package com.example.reminder_domain.model

enum class Instruction {
    AfterEat,
    BeforeEat;

    fun getInstructionId(): String {
        return when(this) {
            AfterEat -> "1"
            BeforeEat -> "2"
        }
    }

    fun getInstructionText(): String {
        return when(this) {
            AfterEat -> "After Eat"
            BeforeEat -> "Before Eat"
        }
    }
}