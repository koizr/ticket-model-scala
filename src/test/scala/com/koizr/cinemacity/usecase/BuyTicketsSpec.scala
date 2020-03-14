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
    Screen(Film("The movie", Time(2)), ScreenNumber(1), at)

  // TODO: TDT にしたい
  // 2020-01-06 は月曜日
  "General customer" should "buy a weekday early show ticket for 1800 yen" in {
    val tickets = BuyTicket(group(CustomerType.General), screen(DateTime(2020, 1, 6, 19)))
    assert(tickets.head.price == JPY(1800))
  }
  it should "buy a weekday late show ticket for 1300 yen" in {
    val tickets = BuyTicket(group(CustomerType.General), screen(DateTime(2020, 1, 6, 20)))
    assert(tickets.head.price == JPY(1300))
  }
  it should "buy a holiday early show ticket for 1800 yen" in {
    val tickets = BuyTicket(group(CustomerType.General), screen(DateTime(2020, 1, 5, 19)))
    assert(tickets.head.price == JPY(1800))
  }
  it should "buy a holiday late show ticket for 1300 yen" in {
    val tickets = BuyTicket(group(CustomerType.General), screen(DateTime(2020, 1, 5, 20)))
    assert(tickets.head.price == JPY(1300))
  }

  "Senior customer" should "buy a weekday early show ticket for 1100 yen" in {
    val tickets = BuyTicket(group(CustomerType.Senior), screen(DateTime(2020, 1, 6, 19)))
    assert(tickets.head.price == JPY(1100))
  }
}
