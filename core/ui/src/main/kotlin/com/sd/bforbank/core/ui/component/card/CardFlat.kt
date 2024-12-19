package com.sd.bforbank.core.ui.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.bforbank.core.ui.designsystem.MainTheme

@Composable
fun CardFlat(
    modifier: Modifier = Modifier,
    rippleColor: Color = MainTheme.colors.neutrals.black100Pressed,
    backgroundColor: Color = MainTheme.colors.neutrals.white200Secondary,
    shape: Shape = RoundedCornerShape(MainTheme.dimens.standard75),
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            draggedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier,
    ) {
        ClickableBox(
            rippleColor = rippleColor,
            backgroundColor = backgroundColor,
            onClick = onClick,
            content = content,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xfff)
@Composable
private fun Preview() {
    Column(modifier = Modifier.padding(16.dp)) {
        CardFlat(modifier = Modifier.fillMaxWidth()) {
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
