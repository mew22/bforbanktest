package com.sd.bforbank.core.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sd.bforbank.core.ui.designsystem.MainTheme

@Composable
fun ClickableBox(
    modifier: Modifier = Modifier,
    rippleColor: Color = MainTheme.colors.neutrals.black100Pressed,
    backgroundColor: Color = MainTheme.colors.neutrals.white100Primary,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = rippleColor),
                enabled = onClick != null,
                onClick = { onClick?.invoke() },
            )
            .then(modifier),
        content = content,
    )
}
