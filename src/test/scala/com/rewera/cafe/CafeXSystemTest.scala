package com.rewera.cafe

import org.scalatest.{FlatSpec, Matchers}

class CafeXSystemTest extends FlatSpec with Matchers {

  "A CafeXSystem" should "return proper cost per item" in {
    val item_1 = List("Cola")
    CafeXSystem.calcTotalBill(item_1) shouldEqual BigDecimal(0.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val item_2 = List("Coffee")
    CafeXSystem.calcTotalBill(item_2) shouldEqual BigDecimal(1.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val item_3 = List("Cheese Sandwich")
    CafeXSystem.calcTotalBill(item_3) shouldEqual BigDecimal(2.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val item_4 = List("Steak Sandwich")
    CafeXSystem.calcTotalBill(item_4) shouldEqual BigDecimal(4.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "return 0 for empty purchases list" in {
    val itemsPurchased = List()

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "return 3.5 for items [\"Cola\", \"Coffee\", \"Cheese Sandwich\"]" in {
    val itemsPurchased = List("Cola", "Coffee", "Cheese Sandwich")

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(3.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }




}
