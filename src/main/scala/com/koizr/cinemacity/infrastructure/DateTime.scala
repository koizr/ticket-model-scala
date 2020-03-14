package com.koizr.cinemacity.infrastructure

import com.koizr.cinemacity.domain.datetime.{DayOfWeek, Friday, Monday, Saturday, Sunday, Thursday, Tuesday, Wednesday, DateTime => DateTimeInterface}
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime => JodaTime}

class DateTime private(value: JodaTime) extends DateTimeInterface {
  private val formatter = DateTimeFormat.forPattern("yyyy/MM/dd(E) HH:mm:ss")

  override def dayOfMonth: Int = value.getDayOfMonth

  override def dayOfWeek: DayOfWeek = value.getDayOfWeek match {
    case 7 => Sunday
    case 1 => Monday
    case 2 => Tuesday
    case 3 => Wednesday
    case 4 => Thursday
    case 5 => Friday
    case 6 => Saturday
  }

  override def hour: Int = value.getHourOfDay

  override def toString: String = formatter.print(value)
}

object DateTime {
  def apply(year: Int, month: Int, day: Int,
            hour: Int = 0, minute: Int = 0, second: Int = 0): DateTime =
    new DateTime(new JodaTime(year, month, day, hour, minute, second))

  def now: DateTime = new DateTime(JodaTime.now())
}
