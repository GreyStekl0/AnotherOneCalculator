package com.github.greysteklo.anotherone.calculator.ui.calculator

import com.github.greysteklo.anotherone.calculator.domain.usecase.CalculateExpressionUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.ClearAllUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.DeleteLastCharacterUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterDecimalUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterNumberUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterOperationUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterPercentUseCase
import javax.inject.Inject

data class CalculatorActions
    @Inject
    constructor(
        val enterNumber: EnterNumberUseCase,
        val deleteLastChar: DeleteLastCharacterUseCase,
        val clearAll: ClearAllUseCase,
        val enterOperation: EnterOperationUseCase,
        val enterDecimal: EnterDecimalUseCase,
        val calculate: CalculateExpressionUseCase,
        val enterPercent: EnterPercentUseCase,
    )
