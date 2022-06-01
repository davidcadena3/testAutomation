package com.test.automation.commons.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.nio.file.Path
import java.nio.file.Paths


class RealExcelFile(private val excelFileName: String) {
    var workBook: XSSFWorkbook? = null

    fun getDataAsJson(): JsonNode? {
        val mapper = ObjectMapper()
        val excelData: ObjectNode = mapper.createObjectNode()
        try {

            readFile()

            // Reading each sheet one by one
            workBook?.getSheetAt(0)?.let { sheet ->
                val headers = mutableListOf<String>()
                val sheetData = mapper.createArrayNode()

                for (i in 0 until sheet.lastRowNum + 1) {
                    val row = sheet.getRow(i)

                    if (i == 0) {
                        for (j in 0 until row.lastCellNum) {
                            headers.add(row.getCell(j).stringCellValue)
                        }
                    } else {
                        val rowData = mapper.createObjectNode()
                        for (j in 0 until headers.size) {
                            val cell = row.getCell(j)
                            val headerName = headers[j]
                            if (cell != null) {
                                when (cell.cellType) {
                                    CellType.FORMULA ->
                                        rowData.put(headerName, cell.cellFormula)

                                    CellType.BOOLEAN ->
                                        rowData.put(headerName, cell.booleanCellValue)

                                    CellType.NUMERIC ->
                                        rowData.put(headerName, cell.numericCellValue)

                                    CellType.BLANK ->
                                        rowData.put(headerName, "")

                                    else ->
                                        rowData.put(headerName, cell.stringCellValue)
                                }
                            } else {
                                rowData.put(headerName, "")
                            }
                        }
                        sheetData.add(rowData)
                    }
                }
                excelData.put("data", sheetData)
            }
            return excelData
        } catch (e: Exception) {
            println(e)
        } finally {
            workBook?.let {
                try {
                    it.close()
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
        return null
    }

    private fun readFile() {
        try {
            val resourceDirectory: Path = Paths.get("src", "test", "resources")
            val absolutePath = resourceDirectory.toFile().absolutePath
            val s = File("$absolutePath/data/$excelFileName")
            val stream = FileInputStream(s)
            workBook = XSSFWorkbook(stream)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}