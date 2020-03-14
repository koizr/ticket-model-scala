package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.datetime.Time
import com.koizr.cinemacity.domain.screen.{Film, Screen, ScreenNumber}
import com.koizr.cinemacity.domain.ticket.JPY
import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._

class BuyTicketsSpec extends FlatSpec {
  def group(customers: CustomerType*): CustomerGroup =
    CustomerGroup(customers)

  def screen(at: DateTime): Screen =
    Screen(
      Film("The movie", Time(2)),
      ScreenNumber(1),
      DateTime(2020, 1, 6, 10)
    )

  "General customer" should "buy a ticket for 1800 yen at Monday 19:00" in {
    val tickets = BuyTicket(group(CustomerType.General), screen(DateTime(2020, 1, 6, 19)))
    assert(tickets.head.price == JPY(1800))
  }
  "Senior customer" should "buy a ticket for 1100 yen at Monday 19:00" in {
    val tickets = BuyTicket(group(CustomerType.Senior), screen(DateTime(2020, 1, 6, 19)))
    assert(tickets.head.price == JPY(1100))
  }
}
