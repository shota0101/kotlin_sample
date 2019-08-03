package last_period

fun main(args: Array<String>) {
    // 残りの時間数
    val leftHours = 24 * 11

    // 1回のクエストで取得できるイベントポイント
    val eventPointPerQuest = 405
    // 1回のクエストで消費するポイント
    val questPointPerQuest = 30
    // イベントポイントの目標値
    val goalEventPoint = 100 * 1000

    // 1ポイント回復のために必要な分数
    val minutePerQuestPoint = 3


    val givenMinutes = leftHours * 60
    println("残された時間(分) : $givenMinutes")

    val givenQuestPoint = givenMinutes / minutePerQuestPoint
    println("残されたクエスト出撃用ポイント : $givenQuestPoint")

    val givenQuestCount = givenQuestPoint / questPointPerQuest
    println("残されたクエスト出撃回数 : $givenQuestCount")

    val givenEventPoint = givenQuestCount * eventPointPerQuest
    println("これから獲得するイベントポイント : $givenEventPoint")

    val currentEventPoint = goalEventPoint - givenEventPoint
    println("現時点で必要なイベントポイント : $currentEventPoint")
}
