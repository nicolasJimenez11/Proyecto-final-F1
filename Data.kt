package app.data

import java.io.File

object DataLoader {

    fun cargarDatos(): Pair<Array<DoubleArray>, IntArray> {
        val rutaArchivo = "src/main/resources/archive/results.csv"
        val lines = File(rutaArchivo).readLines().drop(1) // saltar encabezado

        val x = mutableListOf<DoubleArray>()
        val y = mutableListOf<Int>()

        for (line in lines) {
            val tokens = line.split(",")

            val driverId = tokens[3].toDoubleOrNull()
            val constructorId = tokens[4].toDoubleOrNull()
            val positionOrder = tokens[5].toIntOrNull()

            if (driverId != null && constructorId != null && positionOrder != null) {
                x.add(doubleArrayOf(driverId, constructorId))
                y.add(if (positionOrder == 1) 1 else 0) // 1 si ganó, 0 si no
            }
        }

        return Pair(x.toTypedArray(), y.toIntArray())
    }
}
