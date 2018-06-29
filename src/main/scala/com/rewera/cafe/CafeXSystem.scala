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

    noChargeCost + calcServiceCharge(purchasedItems)
  }

  def calcServiceCharge(purchasedItems: Seq[Item]): BigDecimal = {
    0
  }

}
