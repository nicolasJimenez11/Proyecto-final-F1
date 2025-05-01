package app.data

data class ResultEntry(
    val driverId: Int,
    val raceId: Int,
    val constructorId: Int,
    val positionOrder: Int
)

object Preprocessing {

    fun limpiarDatos(rawData: List<Map<String, String>>): List<ResultEntry> {
        return rawData
            .filter { it["positionOrder"] != null }
            .map {
                ResultEntry(
                    driverId = it["driverId"]!!.toInt(),
                    raceId = it["raceId"]!!.toInt(),
                    constructorId = it["constructorId"]!!.toInt(),
                    positionOrder = it["positionOrder"]!!.toInt()
                )
            }
    }

    fun prepararDatosParaModelo(entries: List<ResultEntry>): Pair<Array<DoubleArray>, IntArray> {
        val x = entries.map {
            doubleArrayOf(
                it.driverId.toDouble(),
                it.constructorId.toDouble()
            )
        }.toTypedArray()

        val y = entries.map {
            if (it.positionOrder == 1) 1 else 0
        }.toIntArray()

        return Pair(x, y)
    }
}

