package com.jimmy.avowsstore.presentation.summary

sealed interface SummaryAction {
    data object Close: SummaryAction
    data object OnTryAgain: SummaryAction
}