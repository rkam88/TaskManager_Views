package net.rusnet.taskmanager.edit.presentation

import androidx.annotation.StringRes
import net.rusnet.taskmanager.commons.domain.model.TaskType

data class EditViewState(
    @StringRes val toolbarTitleStringResId: Int,
    val taskName: String,
    val taskType: TaskType,
    val showDates: Boolean,
    val startDate: String,
    val startTime: String,
    val endDate: String,
    val endTime: String
)