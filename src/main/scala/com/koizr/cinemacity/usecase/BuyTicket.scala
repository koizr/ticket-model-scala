package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.CustomerGroup
import com.koizr.cinemacity.domain.screen.Screen
import com.koizr.cinemacity.domain.ticket.{PriceCalculator, Ticket}

object BuyTicket {
  def apply(group: CustomerGroup, screen: Screen): Seq[Ticket] =
    PriceCalculator.calcGroup(group, screen)
}
