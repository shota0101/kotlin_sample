package image

import image.data.MetaClip
import image.data.Point
import image.data.Size
import image.logic.ClipSearcher
import image.logic.CommonAreaCalculator
import image.wrapper.Clip
import image.wrapper.MyImage

private const val EXT = ".PNG"
private const val ROOT_DIR_PATH = "/Users/shhayash/private/local_only/images/iphone/system-preferences/"
private const val PIV = "ja"
private const val TAR = "cn"
private const val COMMON = "common"
private const val SEARCH = "search"

val PIV_SEARCH_META_CLIP = MetaClip(
        Point(35, 650),
        Size(40, 40))

class CommonAreaClipper(private val fileName: String) {
    private val pivImage = MyImage(read(ROOT_DIR_PATH + PIV + fileName + EXT)!!)
    private val tarImage = MyImage(read(ROOT_DIR_PATH + TAR + fileName + EXT)!!)
    private val clipSearcher = searchMostSimilarPoint(fileName)

    private fun searchMostSimilarPoint(fileName: String): ClipSearcher {
        val pivClip = Clip(pivImage, PIV_SEARCH_META_CLIP)
        val clipSearcher = ClipSearcher(pivClip, tarImage)

        // ↓書き込み処理
        val tarClip = Clip(
                tarImage,
                MetaClip(
                        clipSearcher.mostSimilarPoint,
                        PIV_SEARCH_META_CLIP.size))
        write(pivClip.convertToBufferedImage(), ROOT_DIR_PATH + SEARCH + fileName + PIV + EXT)
        write(tarClip.convertToBufferedImage(), ROOT_DIR_PATH + SEARCH + fileName + TAR + EXT)
        // ↑書き込み処理

        return clipSearcher
    }

    fun save() {
        val commonAreaClipper = CommonAreaCalculator(
                pivImage,
                tarImage,
                PIV_SEARCH_META_CLIP.point,
                clipSearcher.mostSimilarPoint)

        // ↓書き込み処理
        val pivCommonClip = Clip(pivImage, commonAreaClipper.metaClip1)
        val tarCommonClip = Clip(tarImage, commonAreaClipper.metaClip2)
        write(pivCommonClip.convertToBufferedImage(), ROOT_DIR_PATH + COMMON + fileName + PIV + EXT)
        write(tarCommonClip.convertToBufferedImage(), ROOT_DIR_PATH + COMMON + fileName + TAR + EXT)
        // ↑書き込み処理
    }
}

fun main(args: Array<String>) {
    for (i in 1..20)
        CommonAreaClipper("/" + String.format("%03d", i)).save()
}