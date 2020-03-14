package com.koizr.cinemacity.domain.datetime

import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._

class DateTimePeriodSpec extends FlatSpec {
  "Monday" should "be weekday" in {
    val period = DateTimePeriod(DateTime(2020, 1, 6))
    assert(period.day == Day.Weekday)
  }
  "Sunday" should "be holiday" in {
    val period = DateTimePeriod(DateTime(2020, 1, 5))
    assert(period.day == Day.Holiday)
  }
  "1st of month and Wednesday" should "be cinema day" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1))
    assert(period.day == Day.CinemaDay)
  }
  "1st of month and Sunday" should "be cinema day" in {
    val period = DateTimePeriod(DateTime(2017, 1, 1))
    assert(period.day == Day.CinemaDay)
  }

  "0:00" should "be early show" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1, 0, 0, 0))
    assert(period.time == TimePeriod.Early)
  }
  "19:59:59" should "be early show" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1, 19, 59, 59))
    assert(period.time == TimePeriod.Early)
  }
  "20:00" should "be late show" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1, 20, 0, 0))
    assert(period.time == TimePeriod.Late)
  }
  "23:59:59" should "be late show" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1, 23, 59, 59))
    assert(period.time == TimePeriod.Late)
  }
}
