package image.logic

import image.data.Point
import image.data.MetaClip
import image.wrapper.Clip
import image.wrapper.MyImage
import image.wrapper.calcSimilarityList

class ClipSearcher(private val clip: Clip, private val image: MyImage) {

    data class ScorePair(val score: Double, val point: Point)
    private val similarityAverageList = search()
    private val sortedSimilarityAverageList = sort()
    val mostSimilarPoint = sortedSimilarityAverageList[0].point

    private fun search(): List<ScorePair> {
        val similarityAverageList = mutableListOf<ScorePair>()
        val lastX = image.w - clip.w
        val lastH = image.h - clip.h
        for (x in 0..lastX)
            for (y in 0..lastH){
                val point = Point(x, y)
                println("progress : ${100.0 * (x * lastH + y) / (lastX * lastH)}%")
                val score = calcSimilarityList(
                        clip,
                        Clip(
                                image,
                                MetaClip(point, clip.size))
                ).average()
                similarityAverageList.add(ScorePair(score, point))

                // 全く同じ画像があれば探索を中断
                if (score == 0.0) {
                    println("same")
                    return similarityAverageList
                }
            }
        return similarityAverageList
    }

    private fun sort(): List<ScorePair> {
        return similarityAverageList.sortedBy { it.score }
    }
}
