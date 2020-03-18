package com.koizr.cinemacity.domain.datetime

import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._

class DateTimePeriodSpec extends FlatSpec {
  "Monday" should "be weekday and not cinema day" in {
    val period = DateTimePeriod(DateTime(2020, 1, 6))
    assert(period.day == Day.Weekday)
    assert(!period.isCinemaDay)
  }
  "Sunday" should "be holiday and not cinema day" in {
    val period = DateTimePeriod(DateTime(2020, 1, 5))
    assert(period.day == Day.Holiday)
    assert(!period.isCinemaDay)
  }
  "1st of month and Wednesday" should "be weekday and cinema day" in {
    val period = DateTimePeriod(DateTime(2020, 1, 1))
    assert(period.day == Day.Weekday)
    assert(period.isCinemaDay)
  }
  "1st of month and Sunday" should "be holiday and cinema day" in {
    val period = DateTimePeriod(DateTime(2017, 1, 1))
    assert(period.day == Day.Holiday)
    assert(period.isCinemaDay)
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
