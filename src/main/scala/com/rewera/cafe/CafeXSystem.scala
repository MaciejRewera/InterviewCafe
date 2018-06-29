package com.rewera.cafe

sealed trait Item
case object Cola extends Item
case object Coffee extends Item
case object CheeseSandwich extends Item
case object SteakSandwich extends Item

sealed trait ItemType
case object Drink extends ItemType
case object Food extends ItemType

sealed trait ServingTemperature
case object Cold extends ServingTemperature
case object Hot extends ServingTemperature

case class ItemProperties(cost: BigDecimal, itemType: ItemType, servingTemperature: ServingTemperature)


object CafeXSystem {

  val itemsProperties: Map[Item, ItemProperties] = Map(
    Cola -> ItemProperties(BigDecimal(0.5), Drink, Cold),
    Coffee -> ItemProperties(BigDecimal(1.0), Drink, Hot),
    CheeseSandwich -> ItemProperties(BigDecimal(2.0), Food, Cold),
    SteakSandwich -> ItemProperties(BigDecimal(4.5), Food, Hot),
  )

  def calcTotalBill(purchasedItems: Seq[Item]): BigDecimal = {
    val noChargeCost =
      purchasedItems.map(itemsProperties(_).cost).sum.setScale(2, BigDecimal.RoundingMode.HALF_UP)

    noChargeCost + calcServiceCharge(purchasedItems, noChargeCost)
  }

  def calcServiceCharge(purchasedItems: Seq[Item], noChargeCost: BigDecimal): BigDecimal = {
    if ( purchasedItems.map(itemsProperties(_).itemType).contains(Food) )
     calcFoodServiceCharge(purchasedItems, noChargeCost).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    else
      BigDecimal(0.0).setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  private def calcFoodServiceCharge(purchasedItems: Seq[Item], noChargeCost: BigDecimal): BigDecimal = {
    val coldFoodServiceChargeFactor = 0.1
    val hotFoodServiceChargeFactor = 0.2

    val purchasedFood = purchasedItems.filter(itemsProperties(_).itemType == Food)

    if ( purchasedFood.map(itemsProperties(_).servingTemperature).contains(Hot) ) {
      val hotFoodServiceCharge = noChargeCost * hotFoodServiceChargeFactor

      if (hotFoodServiceCharge > 20) 20
      else hotFoodServiceCharge
    }
    else
      noChargeCost * coldFoodServiceChargeFactor
  }

}
