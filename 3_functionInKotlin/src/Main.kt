// Исходный массив
val sourceArray = arrayOf(0, 3, -5, 6, 0, 9, -2, 3, 12, -6, 0)

// 1. Функции с сигнатурами () -> Unit, (Any) -> Any, (Any) -> Collection<Any>
// a. Полноценное объявление функции
fun printArraySize(): Unit {
    println("Размер массива: ${sourceArray.size}")
}

fun doubleValue(value: Any): Any {
    return when (value) {
        is Int -> value * 2
        else -> value
    }
}

fun getPositiveNumbers(value: Any): Collection<Any> {
    return (if (value is Array<*>) value.filter { it is Int && it > 0 } else emptyList()) as Collection<Any>
}

// b. Анонимные функции
val printArraySizeAnon = fun() {
    println("Размер массива (анонимная): ${sourceArray.size}")
}

val doubleValueAnon = fun(value: Any): Any {
    return when (value) {
        is Int -> value * 2
        else -> value
    }
}

val getPositiveNumbersAnon = fun(value: Any): Collection<Any> {
    return (if (value is Array<*>) value.filter { it is Int && it > 0 } else emptyList()) as Collection<Any>
}

// c. Лямбда-функции
val printArraySizeLambda: () -> Unit = { println("Размер массива (лямбда): ${sourceArray.size}") }
val doubleValueLambda: (Any) -> Any = { value -> if (value is Int) value * 2 else value }
val getPositiveNumbersLambda: (Any) -> Collection<Any> = { value ->
    (if (value is Array<*>) value.filter { it is Int && it > 0 } else emptyList()) as Collection<Any>
}

// 2. Функция с переменным количеством аргументов (vararg)
fun countZeros(vararg numbers: Int): Int {
    return numbers.count { it == 0 }
}

// 3. Функция с параметрами по умолчанию
fun filterNumbers(array: Array<Int>, condition: String = "positive"): List<Int> {
    return when (condition) {
        "positive" -> array.filter { it > 0 }
        "negative" -> array.filter { it < 0 }
        else -> array.toList()
    }
}

// 4. Функция с аргументами, которые можно передать только по имени
fun createFilteredMap(minValue: Int = Int.MIN_VALUE, maxValue: Int = Int.MAX_VALUE): Map<Int, Int> {
    val result = mutableMapOf<Int, Int>()
    sourceArray.forEachIndexed { index, num ->
        if (num in minValue..maxValue) result[index] = num
    }
    return result
}

// 5. Функция, принимающая другую функцию в качестве аргумента
fun applyToArray(array: Array<Int>, operation: (Int) -> Int): List<Int> {
    return array.map(operation)
}

// 6. Функция, возвращающая другую функцию
fun getCounter(): (Array<Int>) -> Int {
    return { arr -> arr.count { it % 2 == 0 } }
}

// 7. Рекурсивная функция для замены цикла (подсчёт суммы положительных чисел)
fun sumPositiveNumbers(array: Array<Int>, index: Int = 0, accumulator: Long = 0): Long {
    return if (index >= array.size) {
        accumulator
    } else {
        val newAcc = if (array[index] > 0) accumulator + array[index] else accumulator
        sumPositiveNumbers(array, index + 1, newAcc)
    }
}

fun main() {
    // Тестирование функций
    // 1. Функции с сигнатурами
    printArraySize() // Вывод: Размер массива: 11
    printArraySizeAnon() // Вывод: Размер массива (анонимная): 11
    printArraySizeLambda() // Вывод: Размер массива (лямбда): 11

    println("Удвоение числа 5: ${doubleValue(5)}") // Вывод: 10
    println("Удвоение числа 5 (анонимная): ${doubleValueAnon(5)}") // Вывод: 10
    println("Удвоение числа 5 (лямбда): ${doubleValueLambda(5)}") // Вывод: 10

    println("Положительные числа: ${getPositiveNumbers(sourceArray)}") // Вывод: [3, 6, 9, 3, 12]
    println("Положительные числа (анонимная): ${getPositiveNumbersAnon(sourceArray)}") // Вывод: [3, 6, 9, 3, 12]
    println("Положительные числа (лямбда): ${getPositiveNumbersLambda(sourceArray)}") // Вывод: [3, 6, 9, 3, 12]

    // 2. Функция с vararg
    val zeroCount = countZeros(0, 3, -5, 6, 0, 9, -2, 3, 12, -6, 0)
    println("Количество нулей (vararg): $zeroCount") // Вывод: 3

    // 3. Функция с параметрами по умолчанию
    println("Положительные числа: ${filterNumbers(sourceArray)}") // Вывод: [3, 6, 9, 3, 12]
    println("Отрицательные числа: ${filterNumbers(sourceArray, "negative")}") // Вывод: [-5, -2, -6]

    // 4. Функция с именованными аргументами
    val filteredMap = createFilteredMap(minValue = 0, maxValue = 10)
    println("Отфильтрованный словарь (0..10): $filteredMap") // Вывод: {0=0, 1=3, 3=6, 4=0, 5=9, 7=3, 10=0}

    // 5. Функция, принимающая другую функцию
    val doubledArray = applyToArray(sourceArray) { it * 2 }
    println("Удвоенные элементы: $doubledArray") // Вывод: [0, 6, -10, 12, 0, 18, -4, 6, 24, -12, 0]

    // 6. Функция, возвращающая другую функцию
    val evenCounter = getCounter()
    val evenCount = evenCounter(sourceArray)
    println("Количество чётных чисел: $evenCount") // Вывод: 6

    // 7. Рекурсивная функция
    val positiveSum = sumPositiveNumbers(sourceArray)
    println("Сумма положительных чисел (рекурсия): $positiveSum") // Вывод: 33
}