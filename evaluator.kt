package app.model

import smile.classification.DecisionTree
import smile.data.DataFrame
import smile.data.formula.Formula
import smile.data.vector.DoubleVector
import smile.data.vector.IntVector

object Evaluator {

    // Método para evaluar el modelo
    fun evaluarModelo(modelo: DecisionTree, xTest: Array<DoubleArray>, yTest: IntArray) {
        // Crear columnas para cada característica (Feature) y etiquetas
        val features = xTest[0].indices.map { index ->
            DoubleVector.of("Feature$index", xTest.map { it[index] }.toDoubleArray())
        }.toTypedArray()

        val labels = IntVector.of("label", yTest)

        val testData = DataFrame.of(*features, labels)

        // Imprimir el DataFrame de prueba para verificar las columnas
        println("Datos del DataFrame para la prueba:")
        println(testData)

        // Crear la fórmula para predicción
        val formula = Formula.lhs("label")

        // Hacer las predicciones con el modelo
        val predicciones = modelo.predict(testData)

        // Calcular precisión manualmente
        val accuracy = predicciones.zip(yTest).count { it.first == it.second } / yTest.size.toDouble()

        println("Precisión del modelo: ${accuracy * 100}%")
    }
}
