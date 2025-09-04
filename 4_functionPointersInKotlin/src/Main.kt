// Исходный массив
val sourceArray = arrayOf(0, 3, -5, 6, 0, 9, -2, 3, 12, -6, 0)

// Декоратор 1: Логирование времени выполнения функции
fun <T, R> logExecutionTime(func: (T) -> R): (T) -> R {
    return { input ->
        val startTime = System.currentTimeMillis()
        val result = func(input)
        val endTime = System.currentTimeMillis()
        println("Время выполнения функции: ${endTime - startTime} мс")
        result
    }
}

// Декоратор 2: Проверка на пустой массив
fun <T> checkEmptyArray(func: (Array<T>) -> Int): (Array<T>) -> Int {
    return { array ->
        if (array.isEmpty()) {
            println("Массив пуст, возвращаем 0")
            0
        } else {
            func(array)
        }
    }
}

// Декоратор 3: Кэширование результатов для массива
fun <T> cacheResult(func: (Array<T>) -> Int): (Array<T>) -> Int {
    val cache = mutableMapOf<String, Int>()
    return { array ->
        val key = array.joinToString()
        cache.getOrPut(key) {
            println("Вычисляем результат для массива: ${array.joinToString()}")
            func(array)
        }
    }
}

// Пример функции для подсчёта количества нулей
fun countZeros(array: Array<Int>): Int {
    return array.count { it == 0 }
}

// Пример функции для подсчёта чётных чисел
fun countEvens(array: Array<Int>): Int {
    return array.count { it % 2 == 0 }
}

// Пример функции для суммы положительных чисел
fun sumPositives(array: Array<Int>): Int {
    return array.sumOf { if (it > 0) it else 0 }
}

// Функция для комбинирования декораторов
fun <T> combineDecorators(
    func: (Array<T>) -> Int,
    vararg decorators: ((Array<T>) -> Int) -> (Array<T>) -> Int
): (Array<T>) -> Int {
    return decorators.fold(func) { acc, decorator -> decorator(acc) }
}

fun main() {
    // Применение декораторов по одному
    println("=== Применение декораторов по одному ===")

    // 1. Декоратор logExecutionTime
    val loggedCountZeros = logExecutionTime(::countZeros)
    println("Количество нулей: ${loggedCountZeros(sourceArray)}") // Ожидаемый вывод: 3 + время выполнения

    // 2. Декоратор checkEmptyArray
    val checkedCountEvens = checkEmptyArray(::countEvens)
    println("Количество чётных чисел: ${checkedCountEvens(sourceArray)}") // Ожидаемый вывод: 6
    println("Проверка пустого массива: ${checkedCountEvens(emptyArray())}") // Ожидаемый вывод: 0 (пустой массив)

    // 3. Декоратор cacheResult
    val cachedSumPositives = cacheResult(::sumPositives)
    println("Сумма положительных чисел (1-й вызов): ${cachedSumPositives(sourceArray)}") // Ожидаемый вывод: 33 + сообщение о вычислении
    println("Сумма положительных чисел (2-й вызов): ${cachedSumPositives(sourceArray)}") // Ожидаемый вывод: 33 (из кэша)

    // Применение всех декораторов вместе
    println("\n=== Применение всех декораторов вместе ===")
    val combinedFunction = combineDecorators(
        ::countZeros,
        ::logExecutionTime,
        ::checkEmptyArray,
        ::cacheResult
    )
    println("Количество нулей (комбинированный декоратор): ${combinedFunction(sourceArray)}") // Ожидаемый вывод: 3 + сообщения от всех декораторов
    println("Количество нулей (комбинированный, 2-й вызов): ${combinedFunction(sourceArray)}") // Ожидаемый вывод: 3 (из кэша) + время
    println("Проверка пустого массива (комбинированный): ${combinedFunction(emptyArray())}") // Ожидаемый вывод: 0 + сообщения
}