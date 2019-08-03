package image.wrapper

import image.calcSimilarity
import image.data.MetaClip
import java.awt.Color
import java.awt.image.BufferedImage

class Clip(private val image: MyImage, metaClip: MetaClip) {
    private val x = metaClip.x
    private val y = metaClip.y
    val w = metaClip.w
    val h = metaClip.h
    val size = metaClip.size

    fun get(x: Int, y: Int): Int {
        return image.get(
                x + this.x,
                y + this.y)
    }

    fun convertToBufferedImage(): BufferedImage {
        val newImage = BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR)

//        newImage.setRGB(
//                metaClip.point.x,
//                metaClip.point.y,
//                w,
//                h,
//                image.intArray,
//                0,
//                image.w)
        // FIXME: ↑の処理を↓の処理で代用
        for (x in 0 until w)
            for (y in 0 until h)
                newImage.setRGB(
                        x,
                        y,
                        get(x, y))

        return newImage
    }
}

fun calcSimilarityList(clip1: Clip, clip2: Clip): List<Double> {
    val distanceList = mutableListOf<Double>()
    for (x in 0 until clip1.w)
        for (y in 0 until clip1.h)
            distanceList.add(calcSimilarity(
                    Color(clip1.get(x, y)),
                    Color(clip2.get(x, y))
            ))
    return distanceList
}
