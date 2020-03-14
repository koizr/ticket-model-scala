package com.koizr.cinemacity.domain.ticket

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.datetime.{Day, TimePeriod}
import com.koizr.cinemacity.domain.screen.Screen

object PriceCalculator {
  def calcGroup(group: CustomerGroup, screen: Screen): Seq[Ticket] =
    group.customers.map(calcCustomer(screen))

  private def calcCustomer(screen: Screen)(customer: CustomerType): Ticket = {
    val day = Day(screen.at)
    val time = TimePeriod(screen.at)
    // TODO: パターン作り込む
    val price = JPY(customer match {
      case CustomerType.General => day match {
        case Day.Weekday | Day.Holiday => time match {
          case TimePeriod.Early => 1800
          case TimePeriod.Late => 1300
        }
        case Day.CinemaDay => 1100
      }
      case CustomerType.Senior => 1100
      case _ => ???
    })
    Ticket(screen, price)
  }
}
