package com.jimmy.avowsstore.presentation.summary

sealed interface SummaryEvent {
    data object Close: SummaryEvent
}