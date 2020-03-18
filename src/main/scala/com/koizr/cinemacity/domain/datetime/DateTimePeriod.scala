package com.koizr.cinemacity.domain.datetime


case class DateTimePeriod(day: Day, time: TimePeriod, isCinemaDay: Boolean)

object DateTimePeriod {
  def apply(datetime: DateTime): DateTimePeriod =
    DateTimePeriod(Day(datetime), TimePeriod(datetime), Day.isCinemaDay(datetime))
}

sealed trait Day

object Day {

  object Weekday extends Day

  object Holiday extends Day

  /**
   * 祝日の計算はこのモデリング練習の範囲外なので雑に土日だけを Holiday とする
   */
  def apply(datetime: DateTime): Day =
    datetime.dayOfWeek match {
      case Sunday | Saturday => Holiday
      case _ => Weekday
    }

  // TODO: この雑な実装をなんとかする
  // Day のメソッドは DateTime に持たせるべきかもしれない。
  def isCinemaDay(datetime: DateTime): Boolean = datetime.dayOfMonth == 1
}

sealed trait TimePeriod

object TimePeriod {

  object Early extends TimePeriod

  object Late extends TimePeriod

  def apply(datetime: DateTime): TimePeriod =
    if (datetime.hour < 20) {
      Early
    } else {
      Late
    }

}
