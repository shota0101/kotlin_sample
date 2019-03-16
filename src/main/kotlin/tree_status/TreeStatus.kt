package tree_status

import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class TreeStatus(rootPath: String, private val excludeKeyWord: String) {
    private var fileList: MutableList<File> = mutableListOf()
    private var filteredList: List<File> = listOf()
    private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    init {
        println("--- TreeWalk ---")
        println("rootPath : " + rootPath)

        println("walk...")
        listFile(rootPath)
        println(fileList.size.toString() + " files")

        println("filter...")
        exclude()
        println(filteredList.size.toString() + " files")
    }

    private fun listFile(path: String) {
        File(path).listFiles().forEach {
            if (it.isDirectory) {
                listFile(it.absolutePath)
            } else {
                fileList.add(it)
            }
        }
    }

    private fun exclude() {
        filteredList = fileList.filter {
            it.absolutePath.contains(excludeKeyWord) == false
        }
    }

    // TODO: 標準出力ではなくファイル出力
    fun print() {
        filteredList.sortedBy {
            it.absolutePath
        }.forEach {
            val dateString = simpleDateFormat.format(Date(it.lastModified()))
            // TODO: 相対バスにする
            println("${dateString} ${it.absolutePath}")
        }
    }
}

fun main(args: Array<String>) {
    val excludeKeyWord = ".git"

    val treeStatus = TreeStatus(args[0], excludeKeyWord)
    treeStatus.print()
}