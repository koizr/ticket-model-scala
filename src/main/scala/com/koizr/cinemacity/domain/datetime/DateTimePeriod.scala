package com.koizr.cinemacity.domain.datetime


case class DateTimePeriod(day: Day, time: TimePeriod)

object DateTimePeriod {
  def apply(datetime: DateTime): DateTimePeriod =
    DateTimePeriod(Day(datetime), TimePeriod(datetime))
}

sealed trait Day

object Day {

  object Weekday extends Day

  object Holiday extends Day

  object CinemaDay extends Day

  /**
   * 祝日の計算はこのモデリング練習の範囲外なので雑に土日だけを Holiday とする
   */
  def apply(datetime: DateTime): Day =
    if (datetime.dayOfMonth == 1) {
      CinemaDay
    } else {
      datetime.dayOfWeek match {
        case Sunday | Saturday => Holiday
        case _ => Weekday
      }
    }

}

sealed trait TimePeriod

object TimePeriod {

  object Early extends TimePeriod

  object Late extends TimePeriod

  def apply(datetime: DateTime): TimePeriod =
    if (datetime.hour < 19) {
      Early
    } else {
      Late
    }

}
