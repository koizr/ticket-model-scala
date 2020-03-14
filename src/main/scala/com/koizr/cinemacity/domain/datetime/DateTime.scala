package com.koizr.cinemacity.domain.datetime

trait DateTime {
  def dayOfMonth: Int

  def dayOfWeek: DayOfWeek

  def hour: Int
}

sealed trait DayOfWeek

object Sunday extends DayOfWeek

object Monday extends DayOfWeek

object Tuesday extends DayOfWeek

object Wednesday extends DayOfWeek

object Thursday extends DayOfWeek

object Friday extends DayOfWeek

object Saturday extends DayOfWeek
