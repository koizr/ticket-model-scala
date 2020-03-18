package com.koizr.cinemacity.domain.ticket

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.datetime.{DateTimePeriod, Day, TimePeriod}
import com.koizr.cinemacity.domain.screen.Screen

object PriceCalculator {
  def calcGroup(group: CustomerGroup, screen: Screen): Seq[Ticket] =
    group.customers.map(calcCustomer(screen))

  private def calcCustomer(screen: Screen)(customer: CustomerType): Ticket = {
    val period = DateTimePeriod(screen.at)
    val price = JPY(customer match {
      case CustomerType.CinemaCitizen => period.day match {
        case Day.Weekday => 1000
        case Day.Holiday => period.time match {
          case TimePeriod.Early => 1300
          case TimePeriod.Late => 1000
        }
        // TODO: 平日なら 1000 円になる。映画の日というのは平日や祝日とは別軸のようだ
        case Day.CinemaDay => 1100
      }
      case CustomerType.CinemaCitizenOver60 => 1000
      case CustomerType.General => period.day match {
        case Day.Weekday | Day.Holiday => period.time match {
          case TimePeriod.Early => 1800
          case TimePeriod.Late => 1300
        }
        case Day.CinemaDay => 1100
      }
      case CustomerType.Senior => 1100
      case CustomerType.CollegeStudent => period.day match {
        case Day.Weekday | Day.Holiday => period.time match {
          case TimePeriod.Early => 1500
          case TimePeriod.Late => 1300
        }
        case Day.CinemaDay => 1100
      }
      case CustomerType.HighSchoolStudent => 1000
      case CustomerType.Child => 1000
      case CustomerType.Handicapped => 1000
      case CustomerType.HandicappedUnder18 => 900
      case CustomerType.MICard => period.time match {
        case TimePeriod.Early => 1600
        case TimePeriod.Late => 1300
      }
      case CustomerType.ParkingAreaPark80 => period.time match {
        case TimePeriod.Early => 1400
        case TimePeriod.Late => 1100
      }
    })
    Ticket(screen, price)
  }
}
