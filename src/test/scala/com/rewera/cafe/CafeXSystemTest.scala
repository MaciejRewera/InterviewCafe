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

  it should "return 1.5 for items [Cola, Coffee,]" in {
    val itemsPurchased = List("Cola", "Coffee")

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(1.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "charge 10% for service when cold food was ordered" in {
    val itemsPurchased = List("Cola", "Cheese Sandwich")

    CafeXSystem.calcServiceCharge(itemsPurchased) shouldEqual
      BigDecimal(0.25).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(2.75).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "charge 20% for service when hot food was ordered" in {
    val itemsPurchased = List("Cola", "Steak Sandwich")

    CafeXSystem.calcServiceCharge(itemsPurchased) shouldEqual
      BigDecimal(1.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(6.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "charge 20% for service when both cold and hot food was ordered" in {
    val itemsPurchased = List("Cola", "Steak Sandwich", "Cheese Sandwich")

    CafeXSystem.calcServiceCharge(itemsPurchased) shouldEqual
      BigDecimal(1.4).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(8.4).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  it should "not exceed 20.0 for service when hot food was ordered" in {
    val coffees = List.fill(200)("Coffee")
    val itemsPurchased = "Steak Sandwich" :: coffees

    CafeXSystem.calcServiceCharge(itemsPurchased) shouldEqual
      BigDecimal(20.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual
      BigDecimal(224.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }




}
