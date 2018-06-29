package com.rewera.cafe


object CafeXSystem {

  val itemsPrices: Map[String, BigDecimal] = Map(
    "Cola" -> BigDecimal(0.5),
    "Coffee" -> BigDecimal(1.0),
    "Cheese Sandwich" -> BigDecimal(2.0),
    "Steak Sandwich" -> BigDecimal(4.5),
  )

  def calcTotalBill(purchasedItems: Seq[String]): BigDecimal =
    purchasedItems.map(itemsPrices(_)).sum.setScale(2, BigDecimal.RoundingMode.HALF_UP)

  def calcServiceCharge(purchasedItems: Seq[String]): BigDecimal = {
    0
  }

}
