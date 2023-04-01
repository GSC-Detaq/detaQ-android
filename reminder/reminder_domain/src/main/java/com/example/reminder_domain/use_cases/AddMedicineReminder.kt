package com.example.reminder_domain.use_cases

import com.example.core.utils.Resource
import com.example.core.utils.extensions.asString
import com.example.reminder_domain.model.Instruction
import com.example.core.domain.model.Time
import com.example.reminder_domain.repository.ReminderRepository
import dagger.hilt.android.scopes.ViewModelScoped
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
class AddMedicineReminder @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        medicineName: String,
        medicineDosage: String,
        dateStart: LocalDate,
        dateEnd: LocalDate,
        time: List<Time>,
        instruction: Instruction
    ): Resource<String> {
        val timeFormatted = time.map {
            val hour = if (it.hour < 9) "0${it.hour}" else it.hour
            val minute = if (it.minute < 9) "0${it.minute}" else it.minute
            "$hour:$minute"
        }

        Timber.d(timeFormatted.toString())

        return repository.addMedicineReminder(
            medicineName = medicineName,
            medicineDosage = medicineDosage,
            dateStart = dateStart.asString("yyyy-MM-dd"),
            dateEnd = dateEnd.asString("yyyy-MM-dd"),
            time = timeFormatted.toString().removePrefix("[").removeSuffix("]"),
            instruction = instruction.getInstructionId()
        )
    }
}