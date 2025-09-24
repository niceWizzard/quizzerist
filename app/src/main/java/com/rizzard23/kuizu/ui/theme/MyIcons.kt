package com.rizzard23.kuizu.ui.theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


object MyIcons {
    val QuestionBox: ImageVector
        get() {
            if (_questionBox != null) return _questionBox!!

            _questionBox = ImageVector.Builder(
                name = "Indeterminate_question_box",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(200f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(120f, 760f)
                    verticalLineToRelative(-160f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(560f, 0f)
                    horizontalLineTo(600f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(-160f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(160f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(760f, 840f)
                    moveTo(120f, 200f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(200f, 120f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(80f)
                    horizontalLineTo(200f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(-80f)
                    close()
                    moveToRelative(720f, 0f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(-80f)
                    verticalLineToRelative(-160f)
                    horizontalLineTo(600f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(160f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(840f, 200f)
                    moveTo(480f, 720f)
                    quadToRelative(21f, 0f, 35.5f, -14.5f)
                    reflectiveQuadTo(530f, 670f)
                    reflectiveQuadToRelative(-14.5f, -35.5f)
                    reflectiveQuadTo(480f, 620f)
                    reflectiveQuadToRelative(-35.5f, 14.5f)
                    reflectiveQuadTo(430f, 670f)
                    reflectiveQuadToRelative(14.5f, 35.5f)
                    reflectiveQuadTo(480f, 720f)
                    moveToRelative(-36f, -153f)
                    horizontalLineToRelative(73f)
                    quadToRelative(0f, -34f, 8f, -52f)
                    reflectiveQuadToRelative(35f, -45f)
                    quadToRelative(35f, -35f, 46.5f, -56.5f)
                    reflectiveQuadTo(618f, 362f)
                    quadToRelative(0f, -54f, -39f, -88f)
                    reflectiveQuadToRelative(-99f, -34f)
                    quadToRelative(-50f, 0f, -86f, 26f)
                    reflectiveQuadToRelative(-52f, 74f)
                    lineToRelative(66f, 27f)
                    quadToRelative(7f, -26f, 26.5f, -42.5f)
                    reflectiveQuadTo(480f, 308f)
                    quadToRelative(29f, 0f, 46.5f, 15.5f)
                    reflectiveQuadTo(544f, 365f)
                    quadToRelative(0f, 20f, -9.5f, 37.5f)
                    reflectiveQuadTo(502f, 439f)
                    quadToRelative(-33f, 29f, -45.5f, 56f)
                    reflectiveQuadTo(444f, 567f)
                }
            }.build()

            return _questionBox!!
        }

    private var _questionBox: ImageVector? = null


    val History: ImageVector
        get() {
            if (_History != null) return _History!!

            _History = ImageVector.Builder(
                name = "History",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                    moveTo(480f, 840f)
                    quadToRelative(-138f, 0f, -240.5f, -91.5f)
                    reflectiveQuadTo(122f, 520f)
                    horizontalLineToRelative(82f)
                    quadToRelative(14f, 104f, 92.5f, 172f)
                    reflectiveQuadTo(480f, 760f)
                    quadToRelative(117f, 0f, 198.5f, -81.5f)
                    reflectiveQuadTo(760f, 480f)
                    reflectiveQuadToRelative(-81.5f, -198.5f)
                    reflectiveQuadTo(480f, 200f)
                    quadToRelative(-69f, 0f, -129f, 32f)
                    reflectiveQuadToRelative(-101f, 88f)
                    horizontalLineToRelative(110f)
                    verticalLineToRelative(80f)
                    horizontalLineTo(120f)
                    verticalLineToRelative(-240f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(94f)
                    quadToRelative(51f, -64f, 124.5f, -99f)
                    reflectiveQuadTo(480f, 120f)
                    quadToRelative(75f, 0f, 140.5f, 28.5f)
                    reflectiveQuadToRelative(114f, 77f)
                    reflectiveQuadToRelative(77f, 114f)
                    reflectiveQuadTo(840f, 480f)
                    reflectiveQuadToRelative(-28.5f, 140.5f)
                    reflectiveQuadToRelative(-77f, 114f)
                    reflectiveQuadToRelative(-114f, 77f)
                    reflectiveQuadTo(480f, 840f)
                    moveToRelative(112f, -192f)
                    lineTo(440f, 496f)
                    verticalLineToRelative(-216f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(184f)
                    lineToRelative(128f, 128f)
                    close()
                }
            }.build()

            return _History!!
        }

    private var _History: ImageVector? = null


}