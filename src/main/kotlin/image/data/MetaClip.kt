package image.data

public data class MetaClip(val point: Point, val size: Size) {
    val x = point.x
    val y = point.y
    val w = size.width
    val h = size.height
}