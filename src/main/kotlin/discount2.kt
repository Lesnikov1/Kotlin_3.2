fun main() {

    val result = calculateDiscount()
    if (result >= 0) println(result) else println("Лимит превышен, операция заблокирована")
    resetCounters()
}

val dayLimit = 150_000
val monthLimit = 600_000
var dailyTransfer = 0

fun calculateDiscount(level: String = "Мир", transferPerMonth: Int = 0, presentTransfer: Int = 50000): Int {
    dailyTransfer += presentTransfer
    val monthlyTransfer = transferPerMonth + dailyTransfer
    val discount = if (dailyTransfer <= dayLimit || monthlyTransfer <= monthLimit) {
        when (level) {
            "Mastercard" -> {
                if (transferPerMonth + dailyTransfer - presentTransfer > 75000) {
                    (0.006 * presentTransfer + 20).toInt()
                } else if (transferPerMonth + dailyTransfer <= 75000) {
                    0
                } else {
                    ((transferPerMonth + dailyTransfer - 75000) * 0.006 + 20).toInt()
                }
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
