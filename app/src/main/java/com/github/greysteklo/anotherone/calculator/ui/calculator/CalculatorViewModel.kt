package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import com.github.greysteklo.anotherone.calculator.domain.usecase.CalculateExpressionUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.ClearAllUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.DeleteLastCharacterUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterDecimalUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterNumberUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterOperationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel
    @Inject
    constructor(
        private val enterNumberUseCase: EnterNumberUseCase,
        private val deleteLastCharacterUseCase: DeleteLastCharacterUseCase,
        private val clearAllUseCase: ClearAllUseCase,
        private val enterOperationUseCase: EnterOperationUseCase,
        private val enterDecimalUseCase: EnterDecimalUseCase,
        private val calculateExpressionUseCase: CalculateExpressionUseCase,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CalculatorState())
        val state: StateFlow<CalculatorState> = _state.asStateFlow()

        fun onAction(action: CalculatorAction) {
            when (action) {
                is CalculatorAction.Number -> enterNumber(action.number)
                is CalculatorAction.Decimal -> enterDecimal()
                is CalculatorAction.Clear -> clearAll()
                is CalculatorAction.Operation -> enterOperation(action.operation)
                is CalculatorAction.Calculate -> calculate()
                is CalculatorAction.Delete -> delete()
                is CalculatorAction.Parentheses -> enterParentheses()
                is CalculatorAction.Percent -> enterPercent()
            }
        }

        private fun enterNumber(number: Int) {
            val expression = state.value.expression
            val newExpression = enterNumberUseCase.execute(expression, number)
            _state.update { currentState ->
                currentState.copy(expression = newExpression)
            }
        }

        private fun enterOperation(operation: String) {
            _state.update { currentState ->
                currentState.copy(
                    expression =
                        enterOperationUseCase.execute(
                            currentState.expression,
                            operation,
                        ),
                )
            }
        }

        private fun enterDecimal() {
            _state.update { currentState ->
                currentState.copy(expression = enterDecimalUseCase.execute(currentState.expression))
            }
        }

        private fun clearAll() {
            _state.update {
                clearAllUseCase.execute()
            }
        }

        private fun delete() {
            _state.update { currentState ->
                currentState.copy(
                    expression = deleteLastCharacterUseCase.execute(currentState.expression),
                )
            }
        }

        private fun enterParentheses() {
            // TODO: Implement parentheses logic
            // This is a placeholder for future implementation
        }

        private fun enterPercent() {
            // TODO: Implement percent logic
            // This is a placeholder for future implementation
        }

        private fun calculate() {
            viewModelScope.launch {
                val newState = calculateExpressionUseCase.execute(state.value.expression)
                _state.update { newState }
            }
        }
    }

sealed class CalculatorAction {
    data class Number(
        val number: Int,
    ) : CalculatorAction()

    data class Operation(
        val operation: String,
    ) : CalculatorAction()

    data object Decimal : CalculatorAction()

    data object Clear : CalculatorAction()

    data object Calculate : CalculatorAction()

    data object Delete : CalculatorAction()

    data object Parentheses : CalculatorAction()

    data object Percent : CalculatorAction()
}
