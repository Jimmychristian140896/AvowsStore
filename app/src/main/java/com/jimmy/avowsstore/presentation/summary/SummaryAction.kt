package com.jimmy.avowsstore.presentation.summary

sealed interface SummaryAction {
    data object NavigateBack: SummaryAction
    data object OnTryAgain: SummaryAction
    data object Pay: SummaryAction
}