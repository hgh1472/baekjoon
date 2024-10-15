package test01

fun main() {
    var t = readln().toInt()
    while (t-- > 0) {
        val result = readln()
        var total = 0
        var score = 1
        for (c in result) {
            if (c == 'O') {
                total += score
                score++
            }
            else {
                score = 1
            }
        }
        println(total)
    }
}
