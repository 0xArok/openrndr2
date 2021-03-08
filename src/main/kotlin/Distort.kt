import org.openrndr.KEY_SPACEBAR
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.compositor.*
import org.openrndr.extra.fx.blend.Multiply
import org.openrndr.extra.fx.distort.FluidDistort
import org.openrndr.extra.fx.distort.Perturb
import org.openrndr.extra.gui.GUI
import org.openrndr.extra.gui.addTo
import org.openrndr.extra.noise.simplex
import java.awt.Color
import kotlin.math.cos

fun main() = application {
    configure {
        width = 1280
        height = 920
        title = "MartabakCult"

    }
    program {

        val c = compose {


            layer {
                layer {

                    draw {
                        //val color2 = ColorRGBa.fromHex("#D51F57")

                        drawer.fill = ColorRGBa.fromHex("#FF00EA")
                        drawer.stroke = null
                        drawer.strokeWeight = 5.0
                        val r = simplex(0, seconds * 2.0) * 200.0 + 200.0
                        val x = simplex(1, seconds * 2.0) * 200.0 + width / 2
                        val y = simplex(2, seconds * 2.0) * 200.0 + height / 2
                        drawer.circle(x, y, r)
                    }
                }
                layer {

                    draw {
                        drawer.fill = null
                        drawer.stroke = ColorRGBa.fromHex("#00FFEF")
                        drawer.strokeWeight = 5.0
                        val r = simplex(3, seconds * 1.0) * 200.0 + 200.0
                        val x = simplex(4, seconds * 1.0) * 200.0 + width / 2
                        val y = simplex(5, seconds * 1.0) * 200.0 + height / 2
                        drawer.circle(x, y, r)
                    }
                    blend(Multiply())
                }

                layer {

                    draw {
                        drawer.fill = null
                        drawer.stroke = ColorRGBa.PINK
                        drawer.strokeWeight = 5.0
                        val r = simplex(6, seconds * 4.0) * 200.0 + 200.0
                        val x = simplex(7, seconds * 4.0) * 20.0 + width / 2
                        val y = simplex(8, seconds * 4.0) * 20.0 + height / 2
                        drawer.circle(x, y, r)
                    }
                    blend(Multiply())
                }


                layer {

                    draw {
                        drawer.fill = ColorRGBa.fromHex("#00FFEF")
                        drawer.stroke = null
                        drawer.strokeWeight = 10.0
                        val r = simplex(9, seconds * 4.0) * 200.0 + 200.0
                        val x = simplex(10, seconds * 4.0) * 200.0 + width / 2
                        val y = simplex(11, seconds * 4.0) * 200.0 + height / 2
                        drawer.circle(x, y, r)
                    }
                    blend(Multiply())
                }
                layer {

                    draw {
                        drawer.fill = ColorRGBa.fromHex("#9F0DA1")
                        drawer.stroke = null
                        drawer.strokeWeight = 10.0
                        val r = simplex(12, seconds * 3.5) * 200.0 + 10.0
                        val x = simplex(13, seconds * 3.5) * 20.0 + width / 2
                        val y = simplex(14, seconds * 3.5) * 20.0 + height / 2
                        drawer.circle(x, y, r)
                    }
                    blend(Multiply())
                }
                layer {

                    draw {

                        drawer.fill = ColorRGBa.fromHex("#FF00EA")
                        drawer.stroke = null
                        drawer.strokeWeight = 5.0
                        val r = simplex(0, seconds * 2.0) * 200.0 + 200.0
                        drawer.circle(width / 2.0, height / 2.0, r)
                    }
                }
                post(Perturb()) {


                }
                val filter =  FluidDistort()
                var pause = true
                post(filter) {
                    if (!pause)
                        filter.blend += 2
                }

                keyboard.keyDown.listen {
                    if (it.key == KEY_SPACEBAR) {
                        pause = !pause
                    }
                }
            }

            }

        extend {

            c.draw(drawer)
        }
    }
}

