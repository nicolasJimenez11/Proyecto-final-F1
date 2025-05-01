
package app.model

data class DataSplit(
    val xTrain: Array<DoubleArray>,
    val yTrain: IntArray,
    val xTest: Array<DoubleArray>,
    val yTest: IntArray
)
