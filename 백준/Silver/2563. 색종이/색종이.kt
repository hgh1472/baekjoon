package test01

fun main() {
    var paper = Array(101) { IntArray(101, { 0 }) }
    val count = readln().toInt()
    for (i in 1..count) {
        val pos = readln().split(" ")
        val x = pos[0].toInt()
        val y = pos[1].toInt()
        for (j in x..x + 9) {
            for (k in y..y + 9) {
                paper[j][k] = 1
            }
        }
    }

    var answer = 0
    for (i in 1..100) {
        for (j in 1..100) {
            if (paper[i][j] == 1)
                answer++
        }
    }
    println(answer)
}
