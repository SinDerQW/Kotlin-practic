fun main() {
    // Исходный массив
    val sourceArray = arrayOf(0, 3, -5, 6, 0, 9, -2, 3, 12, -6, 0)

    // 1. Array: Подсчёт количества нулей
    val zeroCountArray = sourceArray.count { it == 0 }
    println("1. Количество нулей (Array): $zeroCountArray") // Ожидаемый вывод: 3

    // 2. IntArray: Подсчёт количества чётных чисел
    val intArray = sourceArray.toIntArray()
    val evenCountIntArray = intArray.count { it % 2 == 0 }
    println("2. Количество чётных чисел (IntArray): $evenCountIntArray") // Ожидаемый вывод: 6

    // 3. List: Подсчёт количества нечётных чисел
    val list = sourceArray.toList()
    val oddCountList = list.count { it % 2 != 0 }
    println("3. Количество нечётных чисел (List): $oddCountList") // Ожидаемый вывод: 5

    // 4. Set: Сумма всех положительных чисел
    val set = sourceArray.toSet()
    val positiveSumSet = set.sumOf { if (it > 0) it.toLong() else 0 }
    println("4. Сумма положительных чисел (Set): $positiveSumSet") // Ожидаемый вывод: 30 (уникальные: 3, 6, 9, 12)

    // 5. MutableList: Создание списка отрицательных чисел
    val negativeMutableList = mutableListOf<Int>()
    sourceArray.forEach { if (it < 0) negativeMutableList.add(it) }
    println("5. Отрицательные числа (MutableList): $negativeMutableList") // Ожидаемый вывод: [-5, -2, -6]

    // 6. MutableSet: Создание множества уникальных чисел
    val uniqueMutableSet = mutableSetOf<Int>()
    sourceArray.forEach { uniqueMutableSet.add(it) }
    println("6. Уникальные числа (MutableSet): $uniqueMutableSet") // Ожидаемый вывод: [0, 3, -5, 6, 9, -2, 12, -6]

    // 7. Map: Подсчёт повторяющихся чисел
    val frequencyMap = sourceArray.groupBy { it }.mapValues { it.value.size }
    val repeatedNumbers = frequencyMap.filter { it.value > 1 }.keys.toList()
    println("7. Повторяющиеся числа (Map): $repeatedNumbers") // Ожидаемый вывод: [0, 3]

    // 8. MutableMap: Числа, кратные трём, с их индексами
    val multiplesOfThreeMap = mutableMapOf<Int, Int>()
    sourceArray.forEachIndexed { index, num -> if (num % 3 == 0 && num != 0) multiplesOfThreeMap[index] = num }
    println("8. Числа, кратные трём с индексами (MutableMap): $multiplesOfThreeMap") // Ожидаемый вывод: {1=3, 3=6, 5=9, 7=3, 8=12, 9=-6}
}