package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.CustomerType
import com.koizr.cinemacity.domain.datetime.Time
import com.koizr.cinemacity.domain.screen.{Film, Screen, ScreenNumber}
import com.koizr.cinemacity.domain.ticket.JPY
import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._

class BuyTicketsSpec extends FlatSpec {
  "General customer" should "buy a ticket for 1800 yen at Monday 19:00" in {
    val screen = Screen(
      Film("The movie", Time(2)),
      ScreenNumber(1),
      DateTime(2020, 1, 6, 10)
    )
    val tickets = BuyTicket(CustomerType.General, screen)
    for (ticket <- tickets) assert(ticket.price == JPY(1800))
  }
}
