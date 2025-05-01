package app.model

import smile.classification.DecisionTree
import smile.data.DataFrame
import smile.data.formula.Formula
import smile.data.vector.DoubleVector
import smile.data.vector.IntVector

object ModelTrainer {

    // Método para dividir los datos en entrenamiento y prueba
    fun dividirDatos(x: Array<DoubleArray>, y: IntArray): DataSplit {
        val split = (0 until x.size).shuffled().chunked((x.size * 0.8).toInt())

        val trainIndices = split[0].toIntArray()
        val testIndices = split[1].toIntArray()

        val xTrain = trainIndices.map { x[it] }.toTypedArray()
        val yTrain = trainIndices.map { y[it] }.toIntArray()

        val xTest = testIndices.map { x[it] }.toTypedArray()
        val yTest = testIndices.map { y[it] }.toIntArray()

        return DataSplit(xTrain, yTrain, xTest, yTest)
    }

    // Método para entrenar un modelo de árbol de decisión
    fun entrenarModelo(xTrain: Array<DoubleArray>, yTrain: IntArray): DecisionTree {
        // Crear columnas para cada característica (Feature) y etiquetas
        val features = xTrain[0].indices.map { index ->
            DoubleVector.of("Feature$index", xTrain.map { it[index] }.toDoubleArray())
        }.toTypedArray()

        val labels = IntVector.of("label", yTrain)

        // Crear el DataFrame con las características y las etiquetas
        val trainData = DataFrame.of(*features, labels)

        // Imprimir el DataFrame para verificar las columnas
        println("Datos del DataFrame para el entrenamiento:")
        println(trainData)

        // Usar una fórmula para indicar la columna objetivo ("label")
        val formula = Formula.lhs("label")

        // Crear y entrenar el modelo
        return DecisionTree.fit(formula, trainData)
    }
}
