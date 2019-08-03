package tree_walk

import java.io.File

class TreeWalk(private val rootPath: String, private val excludeKeyWord: String) {
    private var fileList: MutableList<File> = mutableListOf()
    private var filteredList: List<File> = listOf()
    private var map: MutableMap<String, MutableList<File>> = HashMap()

    init {
        println("--- TreeWalk ---")
        println("rootPath : " + rootPath)

        println("walk...")
        listFile(rootPath)
        println(fileList.size.toString() + " files")

        println("filter...")
        exclude()
        println(filteredList.size.toString() + " files")

        println("convert to map...")
        toMap()
        println(map.size.toString() + " same file names")
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

    // ファイル名 -> 同じファイル名のファイル（複数かも）な構造
    private fun toMap() {
        filteredList.forEach {
            if (map.containsKey(it.name)) {
                map[it.name]?.add(it)
            } else {
                map[it.name] = mutableListOf(it)
            }
        }
    }

    private fun getDiffFiles(treeWalk: TreeWalk): List<File>{
        val diffFiles: MutableList<File> = mutableListOf()
        treeWalk.map.forEach{ (k, v) ->
            if (!map.containsKey(k))
                v.forEach { diffFiles.add(it) }
        }
        return diffFiles
    }

    fun printDiffFiles(target: TreeWalk) {
        println("search file in " + target.rootPath + " but not in " + rootPath + "...")
        val diffFiles: List<File> = getDiffFiles(target)

        println(diffFiles.size.toString() + " files found")
        diffFiles.forEach { println(it.absolutePath) }
    }

    private fun getSameFiles(treeWalk: TreeWalk): List<File>{
        val diffFiles: MutableList<File> = mutableListOf()
        treeWalk.map.forEach{ (k, v) ->
            if (map.containsKey(k))
                v.forEach { diffFiles.add(it) }
        }
        return diffFiles
    }

    fun printSameFiles(target: TreeWalk) {
        println("search file in both file ...")
        val diffFiles: List<File> = getSameFiles(target)

        println(diffFiles.size.toString() + " files found")
        diffFiles.forEach { println("rm \"" + it.absolutePath + "\"") }
    }
}


fun main(args: Array<String>) {
    val excludeKeyWord = ".git"

    val treeWalkLt = TreeWalk(args[0], excludeKeyWord)
    println()

    val treeWalkRt = TreeWalk(args[1], excludeKeyWord)
    println()

    treeWalkLt.printDiffFiles(treeWalkRt)
    println()
    treeWalkRt.printDiffFiles(treeWalkLt)
}