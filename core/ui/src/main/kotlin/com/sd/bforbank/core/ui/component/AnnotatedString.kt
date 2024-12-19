package com.sd.bforbank.core.ui.component

import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spanned
import android.text.SpannedString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.core.text.HtmlCompat
import com.sd.bforbank.core.ui.designsystem.MainTheme

@Composable
fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any): AnnotatedString {
    val resources = resources()
    val density = LocalDensity.current
    val text = remember(id, formatArgs) {
        resources.getText(id, *formatArgs)
    }
    return spannableStringToAnnotatedString(text, density)
}

@Composable
fun annotatedString(text: String): AnnotatedString {
    val density = LocalDensity.current
    return runCatching {
        HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }.map {
        spannableStringToAnnotatedString(it, density)
    }.getOrDefault(AnnotatedString(text))
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

private fun Spanned.toHtmlWithoutParagraphs(): String =
    HtmlCompat.toHtml(this, HtmlCompat.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE)
        .substringAfter("<p dir=\"ltr\">")
        .substringBeforeLast("</p>")

@Suppress("SpreadOperator")
private fun Resources.getText(@StringRes id: Int, vararg args: Any): CharSequence {
    val escapedArgs = args.map {
        if (it is Spanned) it.toHtmlWithoutParagraphs() else it
    }.toTypedArray()
    val resource = SpannedString(getText(id))
    val htmlResource = resource.toHtmlWithoutParagraphs()
    val formattedHtml = String.format(htmlResource, *escapedArgs)
    return HtmlCompat.fromHtml(formattedHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@Composable
private fun spannableStringToAnnotatedString(
    text: CharSequence,
    density: Density,
): AnnotatedString = if (text is Spanned) {
    buildAnnotatedString {
        append(text.toString())
        text.getSpans(0, text.length, Any::class.java).forEach { span ->
            val start: Int = text.getSpanStart(span)
            val end: Int = text.getSpanEnd(span)
            addStyle(
                start = start,
                end = end,
                style = getSpanStyle(span, density)
            )
        }
    }
} else {
    AnnotatedString(text.toString())
}

@Composable
private fun getSpanStyle(span: Any, density: Density): SpanStyle = with(density) {
    when (span) {
        is StyleSpan -> getSpanStyle(span)
        is TypefaceSpan -> getSpanTypeFace(span)
        is AbsoluteSizeSpan -> SpanStyle(fontSize = if (span.dip) span.size.dp.toSp() else span.size.toSp())
        is RelativeSizeSpan -> SpanStyle(fontSize = span.sizeChange.em)
        is StrikethroughSpan -> SpanStyle(textDecoration = TextDecoration.LineThrough)
        is UnderlineSpan -> SpanStyle(textDecoration = TextDecoration.Underline)
        is SuperscriptSpan -> SpanStyle(baselineShift = BaselineShift.Superscript)
        is SubscriptSpan -> SpanStyle(baselineShift = BaselineShift.Subscript)
        is ForegroundColorSpan -> SpanStyle(color = Color(span.foregroundColor))
        is URLSpan -> SpanStyle(
            textDecoration = TextDecoration.Underline,
            color = MainTheme.colors.blues.blue500Primary,
            fontWeight = FontWeight.Bold,
        )

        else -> SpanStyle()
    }
}

private fun getSpanStyle(span: StyleSpan) = when (span.style) {
    Typeface.NORMAL -> SpanStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    )

    Typeface.BOLD -> SpanStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    )

    Typeface.ITALIC -> SpanStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic
    )

    Typeface.BOLD_ITALIC -> SpanStyle(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )

    else -> SpanStyle()
}

private fun getSpanTypeFace(span: TypefaceSpan) = SpanStyle(
    fontFamily = when (span.family) {
        FontFamily.SansSerif.name -> FontFamily.SansSerif
        FontFamily.Serif.name -> FontFamily.Serif
        FontFamily.Monospace.name -> FontFamily.Monospace
        FontFamily.Cursive.name -> FontFamily.Cursive
        else -> FontFamily.Default
    }
)
