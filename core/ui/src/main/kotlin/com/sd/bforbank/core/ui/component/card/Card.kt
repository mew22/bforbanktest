package com.sd.bforbank.core.ui.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.bforbank.core.ui.designsystem.MainTheme

@Composable
fun Card(
    modifier: Modifier = Modifier,
    rippleColor: Color = MainTheme.colors.neutrals.black100Pressed,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 8.dp,
            focusedElevation = 8.dp,
            hoveredElevation = 8.dp,
            draggedElevation = 8.dp,
            disabledElevation = 8.dp,
        ),
        shape = RoundedCornerShape(MainTheme.dimens.standard75),
        colors = CardDefaults.cardColors(
            containerColor = MainTheme.colors.neutrals.white100Primary,
        ),
        modifier = modifier,
    ) {
        ClickableBox(
            rippleColor = rippleColor,
            onClick = onClick,
            content = content,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xfff)
@Composable
private fun Preview() {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "CARD", style = MainTheme.typography.titleMedium)
            }
        }
    }
}
