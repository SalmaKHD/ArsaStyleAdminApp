package com.salmakhd.android.arsastyleadmin.ui.designsystem

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salmakhd.android.arsastyleadmin.ui.theme.ArsaStyleAdminTheme

/**
 * Should not be used with a large data set due to animation overhead.
 */
@Composable
fun ArsaCandleGraph(
    modifier: Modifier = Modifier,
    valuesList: List<Int>,
    labelsList: List<String> = emptyList(),
    labelStyle: TextStyle = MaterialTheme.typography.h2.copy(fontSize = 10.sp, color =  androidx.compose.material3.MaterialTheme.colorScheme.onPrimary),
    highlightedBarIndex: Int,
    scale: Int = 24
) {
    val highlightedBarColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
    val unhighlightedBarColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary

    // define max width for each bar
    val maxAllowedBarWidth = 50.dp

    // get number of bar required
    val numberOfValues = valuesList.size

    val textMeasurer = rememberTextMeasurer()
    Box(modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            // define available space for each bar (including space between consecutive bars)
            val totalAvailableHorizontalBarSpace = size.width/numberOfValues
            // prevent bars from being too wide
            val barWidth =
                if (totalAvailableHorizontalBarSpace < maxAllowedBarWidth.toPx()) totalAvailableHorizontalBarSpace
                else maxAllowedBarWidth.toPx()
            // define how wide gaps be
            val spaceBetweenBars = barWidth*0.3f


            valuesList
                // add validation logic to not exceed maxSleepNumber
                .map { if (it < scale) it else scale}
                .forEachIndexed{ index, value ->
                    val brush = Brush.verticalGradient(
                        // 0f to Color.Gray,
                        0f to   if (index == highlightedBarIndex)  highlightedBarColor
                        else unhighlightedBarColor,
                        0f to
                                if (index == highlightedBarIndex)  highlightedBarColor
                                else unhighlightedBarColor
                    )
                    val labelHeight = 20.dp.toPx()
                    // calculate bar height with respect to max sleep hours
                    // leave room for labels also
                    val barHeight = value/scale.toFloat() * (size.height - labelHeight.times(2))


                    // draw value on top of each bar
                    if (index < labelsList.size) {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = value.toString(),
                            topLeft = Offset(
                                x = (if (index == 0) 0f else barWidth * index),
                                y = size.height - barHeight - labelHeight.times(2)
                            ),
                            style = labelStyle.copy(color = Color.White)
                        )
                    }

                    // draw labels
                    if (index < labelsList.size) {
                        drawText(
                            textMeasurer = textMeasurer,
                            text = labelsList[index],
                            topLeft = Offset(
                                x = (if (index == 0) 0f else barWidth * index) + barWidth.div(4),
                                y = size.height - labelHeight
                            ),
                            style = labelStyle.copy(color = Color.White)
                        )
                    }

                    drawRoundRect(
                        brush = brush,
                        topLeft = Offset(
                            x = if(index == 0) 0f else barWidth * index,
                            // bottom to top
                            y = size.height - labelHeight
                        ),
                        size = Size(
                            // actual bar width
                            width = barWidth,
                            height = -barHeight
                        ),
                        cornerRadius = CornerRadius(3.dp.toPx())
                    )
                    // draw border
                    drawRoundRect(
                        style = Stroke(width = 2.dp.toPx()),
                        color = Color.Black,
                        topLeft = Offset(
                            x = if(index == 0) 0f else barWidth * index,
                            // bottom to top
                            y = size.height - labelHeight
                        ),
                        size = Size(
                            // actual bar width
                            width = barWidth,
                            height = -barHeight
                        ),
                        cornerRadius = CornerRadius(3.dp.toPx())
                    )
                }
        }
    }
}

@Preview
@Composable
fun NirvanaGraphPreview() {
    ArsaStyleAdminTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), contentAlignment = Alignment.Center) {
            ArsaCandleGraph(
                modifier = Modifier.fillMaxSize(),
//        sleepHoursList = listOf(
//            14, 7, 6, 9, 10, 14,2,3,4,5,6,7,8,7,6,5,6,7,7,7,5,4,3,
//            4,5,6,8,12,4,3,0,12,14,5,3,14,13, 14, 12, 14, 11, 10, 4,
//            6, 7, 4,8,7,9,9,9,9,10,11,12,13,5,6,7,8,9,7,6,12,13, 8, 3, 14 , 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15154444),,
                valuesList = listOf(
                    11,
                    12,
                    13,
                    5,
                    6,
                    7,
                    8,
                    9,
                    7,
                    6,
                    12,
                    13,
                    8,
                    3,
                    14,
                    4,
                    14,
                    14,
                    15,
                    15154444
                ),
                labelsList = listOf(
                    "f",
                    "s",
                    "s",
                    "f",
                    "f",
                    "s",
                    "s",
                    "f",
                    "f",
                    "s",
                    "s",
                    "f",
                    "f",
                    "s",
                    "s",
                    "f",
                    "f",
                    "s",
                    "s",
                    "f"
                ),
                highlightedBarIndex = 2
            )
        }
    }
}