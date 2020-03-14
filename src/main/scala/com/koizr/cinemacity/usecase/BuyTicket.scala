package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.CustomerGroup
import com.koizr.cinemacity.domain.screen.Screen
import com.koizr.cinemacity.domain.ticket.{JPY, Ticket}

object BuyTicket {
  def apply(group: CustomerGroup, screen: Screen): Seq[Ticket] =
  // TODO: ちゃんと実装する
    Array(Ticket(screen, JPY(1800)))
}
