package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.screen.Screen
import com.koizr.cinemacity.domain.ticket.{PriceCalculator, Ticket}

object BuyTicket {
  def apply(group: CustomerGroup, screen: Screen): Either[FailedToBuyTicket, Seq[Ticket]] = {
    if (handicappedPartnerIsTooMany(group)) {
      Left(HandicappedPartnerIsMoreThanHandicapped)
    } else {
      Right(PriceCalculator.calcGroup(group, screen))
    }
  }

  private def handicappedPartnerIsTooMany(group: CustomerGroup): Boolean = {
    val handicapped = group.customers.count(c => c.isInstanceOf[CustomerType.Handicapped.type])
    val handicappedPartner = group.customers.count(c => c.isInstanceOf[CustomerType.HandicappedPartner.type])
    lazy val handicappedU18 = group.customers.count(c => c.isInstanceOf[CustomerType.HandicappedUnder18.type])
    lazy val handicappedU18Partner = group.customers.count(c => c.isInstanceOf[CustomerType.HandicappedUnder18Partner.type])
    handicapped < handicappedPartner || handicappedU18 < handicappedU18Partner
  }
}

trait FailedToBuyTicket

object HandicappedPartnerIsMoreThanHandicapped extends FailedToBuyTicket
