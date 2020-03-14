package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.CustomerType
import com.koizr.cinemacity.domain.screen.Screen
import com.koizr.cinemacity.domain.ticket.{JPY, Ticket}

object BuyTicket {
  // TODO: CustomerGroup にする
  def apply(customerType: CustomerType, screen: Screen): Seq[Ticket] =
  // TODO: ちゃんと実装する
    Array(Ticket(screen, JPY(1800)))
}
