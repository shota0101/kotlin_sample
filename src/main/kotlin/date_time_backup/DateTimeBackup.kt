package date_time_backup

import java.io.*
import java.text.SimpleDateFormat
import java.util.*


private fun write(path: String, text: String) {
    try {
        val file = FileWriter(path)
        val pw = PrintWriter(BufferedWriter(file))

        pw.println(text)

        pw.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

private fun read(path: String): MutableList<String> {
    val inputStream: InputStream = File(path).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            lineList.add(it)
        }
    }
    return lineList
}

private fun copy(orgPath: String, dstPath: String) {
    val fileIn = File(orgPath)
    val fileOut = File(dstPath)

    val inputChannel = FileInputStream(fileIn).channel
    val outputChannel = FileOutputStream(fileOut).channel

    inputChannel.transferTo(0, inputChannel.size(), outputChannel)
}

private fun saveLastModifiedDateAndCopy(orgPath: String, lastModifiedDateString: String, lastModifiedDateFilePath: String) {
    write(lastModifiedDateFilePath, lastModifiedDateString)
    val dstPath = "${File(lastModifiedDateFilePath).parent}/${lastModifiedDateString}.kdbx"
    copy(orgPath, dstPath)
}


fun main(args: Array<String>) {
    val filePath = args[0]
    val lastModifiedDateFilePath = args[1]

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")

    val file = File(filePath)
    val lastModifiedDateString = simpleDateFormat.format(Date(file.lastModified()))

    val lastModifiedDateFile = File(lastModifiedDateFilePath)
    if (!lastModifiedDateFile.exists())
        saveLastModifiedDateAndCopy(
                filePath,
                lastModifiedDateString,
                lastModifiedDateFilePath)
    else
        if (lastModifiedDateString != read(lastModifiedDateFilePath)[0])
            saveLastModifiedDateAndCopy(
                    filePath,
                    lastModifiedDateString,
                    lastModifiedDateFilePath)
}