package com.rewera.cafe

import org.scalatest.{FlatSpec, Matchers}

class CafeXSystemTest extends FlatSpec with Matchers {

  "A CafeXSystem" should "return proper properties per item" in {
    CafeXSystem.itemsProperties(Cola).cost shouldEqual BigDecimal(0.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    CafeXSystem.itemsProperties(Cola).itemType shouldEqual Drink
    CafeXSystem.itemsProperties(Cola).servingTemperature shouldEqual Cold

    CafeXSystem.itemsProperties(Coffee).cost shouldEqual BigDecimal(1.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    CafeXSystem.itemsProperties(Coffee).itemType shouldEqual Drink
    CafeXSystem.itemsProperties(Coffee).servingTemperature shouldEqual Hot

    CafeXSystem.itemsProperties(CheeseSandwich).cost shouldEqual BigDecimal(2.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    CafeXSystem.itemsProperties(CheeseSandwich).itemType shouldEqual Food
    CafeXSystem.itemsProperties(CheeseSandwich).servingTemperature shouldEqual Cold

    CafeXSystem.itemsProperties(SteakSandwich).cost shouldEqual BigDecimal(4.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    CafeXSystem.itemsProperties(SteakSandwich).itemType shouldEqual Food
    CafeXSystem.itemsProperties(SteakSandwich).servingTemperature shouldEqual Hot
  }

  it should "return 0 for empty purchases list" in {
    val itemsPurchased = List()
    val expectedTotalPrice = BigDecimal(0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

  it should "return 1.5 for items [Cola, Coffee]" in {
    val itemsPurchased = List(Cola, Coffee)
    val expectedTotalPrice = BigDecimal(1.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

  it should "charge 10% for service when cold food was ordered" in {
    val itemsPurchased = List(Cola, CheeseSandwich)
    val noChargeCost = BigDecimal(2.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val expectedServiceCharge = BigDecimal(0.25).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val expectedTotalPrice = BigDecimal(2.75).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcServiceCharge(itemsPurchased, noChargeCost) shouldEqual expectedServiceCharge
    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

  it should "charge 20% for service when hot food was ordered" in {
    val itemsPurchased = List(Cola, SteakSandwich)
    val noChargeCost = BigDecimal(5.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val expectedServiceCharge = BigDecimal(1.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val expectedTotalPrice = BigDecimal(6.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcServiceCharge(itemsPurchased, noChargeCost) shouldEqual expectedServiceCharge
    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

  it should "charge 20% for service when both cold and hot food was ordered" in {
    val itemsPurchased = List(Cola, SteakSandwich, CheeseSandwich)
    val noChargeCost = BigDecimal(7.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val expectedServiceCharge = BigDecimal(1.4).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val expectedTotalPrice = BigDecimal(8.4).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcServiceCharge(itemsPurchased, noChargeCost) shouldEqual expectedServiceCharge
    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

  it should "not exceed 20.0 for service when hot food was ordered" in {
    val coffees = List.fill(200)(Coffee)
    val itemsPurchased = SteakSandwich :: coffees
    val noChargeCost = BigDecimal(204.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    val expectedServiceCharge = BigDecimal(20.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val expectedTotalPrice = BigDecimal(224.5).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    CafeXSystem.calcServiceCharge(itemsPurchased, noChargeCost) shouldEqual expectedServiceCharge
    CafeXSystem.calcTotalBill(itemsPurchased) shouldEqual expectedTotalPrice
  }

}
