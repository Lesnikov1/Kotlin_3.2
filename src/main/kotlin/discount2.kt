fun main() {

    val result = calculateDiscount(0, 50_000, "Мир")
    if (result >= 0) println(result) else println("Лимит превышен, операция заблокирована")
    resetCounters()
}

val dayLimit = 150_000
val monthLimit = 600_000
var dailyTransfer = 0

fun calculateDiscount(transferPerMonth: Int, presentTransfer: Int, level: String): Int {
    dailyTransfer += presentTransfer
    val monthlyTransfer = transferPerMonth + dailyTransfer
    val discount = if (dailyTransfer <= dayLimit || monthlyTransfer <= monthLimit) {
        when (level) {
            "Mastercard" -> {
                if (transferPerMonth > 75000) {
                    (0.006 * presentTransfer + 20).toInt()
                } else if (monthlyTransfer <= 75000) {
                    0
                } else if (monthlyTransfer - presentTransfer <= 75000) {
                    ((monthlyTransfer - 75000) * 0.006 + 20).toInt()
                } else (0.006 * presentTransfer + 20).toInt()
            }
            "Visa" -> {
                if (0.0075 * presentTransfer < 35) {
                    35
                } else (0.0075 * presentTransfer).toInt()
            }
            else -> {
                0
            }
        }

    } else -1
    return discount
}

fun resetCounters() {
    dailyTransfer = 0
}
