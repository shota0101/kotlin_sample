package image.wrapper

import image.data.Point
import java.awt.image.BufferedImage

class MyImage(private val image: BufferedImage) {
    val w = image.width
    val h = image.height
    private val intArray = imageToIntArray(image)

    fun get(point: Point): Int {
        return get(point.x, point.y)
    }

    fun get(x: Int, y: Int): Int {
        if (image.width <= x)
            println("(x, w) = ($x, $w)")
        if (image.height <= y)
            println("(y, h) = ($y, $h)")
        val index = w * y + x
        return intArray[index]
    }

    private fun imageToIntArray(image: BufferedImage): IntArray {
        val intArray = IntArray(w * h)
        image.getRGB(0, 0, w, h, intArray, 0, w)
        return intArray
    }

    fun convertToBufferedImage(): BufferedImage {
        val newImage = BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR)
        newImage.setRGB(0, 0, w, h, intArray, 0, w)
        return newImage
    }
}
