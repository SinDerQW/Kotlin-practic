fun ifSum() {
    try {
        println("Введите значение для аргументов: ")
        val num1 = readln().toInt()
        val num2 = readln().toInt()

        if (num1 in 10..99 && num2 in 10..99) println("Сумма аргументов является 4-х значной?  ${if((num1 * num2) in 1000..9999) true else false}")
        else throw Exception("Число не является 2-х значным!")

    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

fun searchMinimum3element(){
    println("Введите коллекцию для разделения элементов коллекции используйте пробел")
    var input = readln()
    var numbers: List<Int> = input.split(" ").map { it.toInt() }.sorted()

    println("Вывод 3-х минимальных значений в неизменяемой коллекции: ${numbers[0]} ${numbers[1]} ${numbers[2]}")
}

fun mutableCollection(){
    try {
        val numbers = mutableListOf(10, 20, 30, 40, 50, 60, 70, 80)

        println("Введите начальный индекс:")
        val startIndex = readln().toInt()

        println("Введите конечный индекс:")
        val endIndex = readln().toInt()

        if (startIndex < 0 || endIndex >= numbers.size || startIndex > endIndex) {
            throw IllegalArgumentException("Неверный диапазон индексов!")
        }

        val subNumbers = numbers.subList(startIndex, endIndex + 1).toMutableList()

        println("Исходный список: $numbers")
        println("Новый список: $subNumbers")

        subNumbers.add(999)
        subNumbers.removeAt(0)

        println("После изменений: $subNumbers")

    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

fun collectionInCollection(){
    try{
        val matrix: List<List<Int>> = listOf(
            listOf(3, 1, 4, 1),
            listOf(5, 9, 2, 6),
            listOf(5, 3, 5, 3)
        )

        val rows = matrix.size
        val cols = matrix[0].size

        println("Матрица:")
        matrix.forEach { println(it) }

        println("\nМинимумы по строкам:")
        for (i in 0 until rows) {
            val row = matrix[i]
            val minValue = row.minOrNull()!!
            val count = row.count { it == minValue }
            println("Строка $i: минимум = $minValue, количество = $count")
        }

        println("\nМинимумы по столбцам:")
        for (j in 0 until cols) {
            val column = (0 until rows).map { matrix[it][j] }
            val minValue = column.minOrNull()!!
            val count = column.count { it == minValue }
            println("Столбец $j: минимум = $minValue, количество = $count")
        }
    } catch (e: Exception){
        println("Ошибка: ${e.message}")
    }
}

fun palidroms(){
    println("Введите текст:")
    val text = readln()

    val palindromeCount = text
        .split(" ", ",", ".", "!", "?", ";", ":", "-", "—")
        .filter { it.isNotBlank() }
        .map { it.lowercase() }
        .count { it == it.reversed() }

    println("Найдено палиндромов: $palindromeCount")
}
fun main() {
    try {
        println("Выберите номер задания: ")
        val mode = readln().toInt()
        when (mode){
            1 -> ifSum()
            2 -> searchMinimum3element()
            3 -> mutableCollection()
            4 -> collectionInCollection()
            5 -> palidroms()
        }

    }catch (e: Exception){ println(e.message)}
}