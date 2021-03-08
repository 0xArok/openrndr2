package point_and_line

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.noise.valueLinear
import org.openrndr.math.map

fun main() = application {
    configure {
        width = 1280 / 2
        height = 980
    }

    program {

        var randSeed = 1
        var gridStep = 50
        var pntSize = 25

        keyboard.character.listen {
            if(it.character == 'r') { randSeed = (Math.random() * 1000).toInt() }
            if(it.character == '+') {gridStep+=5; pntSize+=5}
            if(it.character == '-') { if(gridStep > 5){ gridStep -=5; pntSize -=5} }
            if(it.character == 'w') pntSize +=5
            if(it.character == 'x') if(pntSize>5){pntSize -=5}
        }

        extend {
            drawer.background(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.fromHex("#FF33A8")
            drawer.stroke = null

            for (y in pntSize until height-pntSize step gridStep) {
                for (x in pntSize until width-pntSize step gridStep) {
                    val randVal = valueLinear(randSeed, x.toDouble(), y.toDouble()) * 12.0 + 12.0
                    val cutOffVal = map(0.0, height.toDouble(), 0.0, 12.0, mouse.position.y)
                    if(randVal > cutOffVal) {
                        drawer.circle(x + pntSize.toDouble(), y + pntSize.toDouble(), pntSize.toDouble())
                    }
                }
            }
        }
    }
}