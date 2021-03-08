import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.GlyphMetrics
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.extra.noise.valueLinear
import org.openrndr.math.map
import kotlin.math.cos

fun main() = application {
    configure {
        width = 1080
        height = 1080
    }

    program {

        var randSeed = 1
        var charSize = 52.0

        var font = loadFont("data/fonts/FiraCode-Bold.ttf", charSize)

        keyboard.character.listen {
            if (it.character == 'r') { randSeed = (Math.random() * 1000).toInt() }
            if (it.character == '+') {
                charSize++
                font = loadFont("data/fonts/FiraCode-Bold.ttf", charSize)
            }
            if (it.character == '-') {
                println(charSize)
                if (charSize>1) {
                    charSize--
                    font = loadFont("data/fonts/FiraCode-Bold.ttf", charSize)
                }
            }
        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.WHITE
            drawer.stroke = null
            drawer.fontMap = font

            val xMax = 1080 / charSize.toInt()
            val yMax = 1080 / charSize.toInt()
            val glyphMin = cos(seconds).coerceIn(33.0, 126.0).toInt()
            val glyphMax = cos(seconds).coerceIn(33.0, 126.0).toInt()

            for (y in 1 until yMax) {
                for (x in 1 until xMax) {

                    val range = glyphMax - glyphMin

                    val randVal = glyphMin + (valueLinear(randSeed, x.toDouble(), y.toDouble()) * 0.5 + 0.5) * range

                    val c = randVal.toChar().toString()

                    val xx = x * charSize
                    val yy = y * charSize

                    drawer.text(text = c, x = xx, y = yy)
                }
            }
        }
    }
}

private operator fun Double.invoke(toString: String): Byte {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
