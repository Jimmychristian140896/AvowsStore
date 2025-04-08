package com.jimmy.avowsstore.presentation.summary

sealed interface SummaryEvent {
    data object NavigateBack: SummaryEvent
    data object Pay: SummaryEvent
}