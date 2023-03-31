package com.example.reminder_presenter.reminder.medicine.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral60
import com.example.reminder_domain.model.Instruction
import com.example.reminder_presenter.R

@Composable
fun InstructionDropDown(
    instruction: Instruction?,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectInstruction: (Instruction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            Instruction.values().forEach { instruction ->
                DropdownMenuItem(
                    onClick = { onSelectInstruction(instruction) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = instruction.getInstructionText()
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .border(
                    width = 1.dp,
                    color = Neutral60,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = instruction?.getInstructionText() ?: "Use Instructions"
            )

            Icon(
                imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if(isOpen) stringResource(id = R.string.close) else stringResource(id = R.string.open)
            )
        }
    }
}

@Preview
@Composable
fun RoleDropDownPreview() {
    DetaQTheme {
        InstructionDropDown(
            instruction = null,
            isOpen = false,
            onClick = { },
            onDismiss = {  },
            onSelectInstruction = {  }
        )
    }
}