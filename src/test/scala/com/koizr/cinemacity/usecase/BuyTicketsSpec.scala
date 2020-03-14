package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.datetime.Time
import com.koizr.cinemacity.domain.screen.{Film, Screen, ScreenNumber}
import com.koizr.cinemacity.domain.ticket.JPY
import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor3

class BuyTicketsSpec extends FlatSpec {
  def group(customers: CustomerType*): CustomerGroup =
    CustomerGroup(customers)

  def screen(at: DateTime): Screen =
    Screen(Film("The movie", Time(2)), ScreenNumber(1), at)

  // 2020-01-06 は月曜日
  val tests: TableFor3[CustomerGroup, Screen, Seq[JPY]] = Table(
    ("CustomerGroup", "Screen", "Price"),
    (group(CustomerType.General), screen(DateTime(2020, 1, 6, 19)), Seq(JPY(1800))),
    (group(CustomerType.General), screen(DateTime(2020, 1, 6, 20)), Seq(JPY(1300))),
    (group(CustomerType.General), screen(DateTime(2020, 1, 5, 19)), Seq(JPY(1800))),
    (group(CustomerType.General), screen(DateTime(2020, 1, 5, 20)), Seq(JPY(1300))),
    (group(CustomerType.Senior), screen(DateTime(2020, 1, 6, 19)), Seq(JPY(1100))),
  )

  forAll(tests) { (group, screen, expectedPrices) =>
    s"$group" should s"buy ticket of screen that starts from ${screen.at} for $expectedPrices" in {
      val tickets = BuyTicket(group, screen)
      for ((ticket, expectedPrice) <- tickets.zip(expectedPrices)) {
        assert(ticket.price == expectedPrice)
      }
    }
  }
}
