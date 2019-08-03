package image.logic

import image.data.MetaClip
import image.data.Point
import image.data.Size
import image.wrapper.MyImage

class CommonAreaCalculator(image1: MyImage, image2: MyImage, point1: Point, point2: Point) {
    private val yDistanceFromPointToZero = getSmaller(
            point1.y, point2.y)
    private val yDistanceFromPointToHeight = getSmaller(
            (image1.h - 1) - point1.y,
            (image2.h - 1) - point2.y)
    val metaClip1 = getCommonArea(image1, point1)
    val metaClip2 = getCommonArea(image2, point2)

    private fun getCommonArea(image: MyImage, point: Point): MetaClip {
        return MetaClip(
                Point(
                        0,
                        point.y - yDistanceFromPointToZero),
                Size(
                        image.w,
                        yDistanceFromPointToZero + yDistanceFromPointToHeight + 1
                ))
    }
}

private fun getSmaller(value1: Int, value2: Int): Int {
    return if (value1 < value2) value1 else value2
}
