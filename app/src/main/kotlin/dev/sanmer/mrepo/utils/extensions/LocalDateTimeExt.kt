package dev.sanmer.mrepo.utils.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toDateTime(): LocalDateTime {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(TimeZone.currentSystemDefault())
}

fun Long.toDate(): LocalDate {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
}

fun LocalDateTime.Companion.now() =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

fun Double.toDateTime(): LocalDateTime {
    val newValue = if (this <= 1_000_000_000_000.0) this * 1000
    else this
    return Instant.fromEpochMilliseconds(newValue.toLong())
        .toLocalDateTime(TimeZone.currentSystemDefault())
}

fun Double.toDate() = toDateTime().date