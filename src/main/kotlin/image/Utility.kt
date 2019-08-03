package image

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

fun read(path: String): BufferedImage?{
    try {
        return ImageIO.read(File(path))
    } catch (e: IOException) {
        e.printStackTrace()
        System.exit(-1)
    }
    return null
}

fun write(image: BufferedImage, path: String, formatName: String = "png") {
    try {
        ImageIO.write(image, formatName, File(path))
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun calcSimilarity(color1: Color, color2: Color): Double {
    return calcEuclideanDistance(color1, color2)
}

fun calcEuclideanDistance(color1: Color, color2: Color): Double {
    return Math.sqrt(
            Math.pow(color1.red.toDouble() - color2.red, 2.0) +
                    Math.pow(color1.green.toDouble() - color2.green, 2.0) +
                    Math.pow(color1.blue.toDouble() - color2.blue, 2.0)
    )
}

