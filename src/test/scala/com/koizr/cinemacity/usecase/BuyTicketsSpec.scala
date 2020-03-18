package com.koizr.cinemacity.usecase

import com.koizr.cinemacity.domain.customer.{CustomerGroup, CustomerType}
import com.koizr.cinemacity.domain.datetime.Time
import com.koizr.cinemacity.domain.screen.{Film, Screen, ScreenNumber}
import com.koizr.cinemacity.domain.ticket.JPY
import com.koizr.cinemacity.infrastructure.DateTime
import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor3

class BuyTicketsSpec extends FlatSpec {
  def group(customers: CustomerType*): CustomerGroup =
    CustomerGroup(customers)

  def screen(at: DateTime): Screen =
    Screen(Film("The movie", Time(2)), ScreenNumber(1), at)

  private val weekDayEarlyTime = screen(DateTime(2020, 1, 6, 19))
  private val weekDayLateTime = screen(DateTime(2020, 1, 6, 20))
  private val holyDayEarlyTime = screen(DateTime(2020, 1, 5, 19))
  private val holyDayLateTime = screen(DateTime(2020, 1, 5, 20))
  private val weekDayEarlyTimeCinemaDay = screen(DateTime(2020, 1, 1, 19))
  private val weekDayLateTimeCinemaDay = screen(DateTime(2020, 1, 1, 20))
  private val holyDayEarlyTimeCinemaDay = screen(DateTime(2017, 1, 1, 19))
  private val holyDayLateTimeCinemaDay = screen(DateTime(2017, 1, 1, 20))

  // 2020-01-06 は月曜日
  val tests: TableFor3[CustomerGroup, Screen, Seq[JPY]] = Table(
    ("CustomerGroup", "Screen", "Price"),
    (group(CustomerType.CinemaCitizen), weekDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizen), weekDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizen), holyDayEarlyTime, Seq(JPY(1300))),
    (group(CustomerType.CinemaCitizen), holyDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizen), weekDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizen), weekDayLateTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizen), holyDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.CinemaCitizen), holyDayLateTimeCinemaDay, Seq(JPY(1000))),

    (group(CustomerType.CinemaCitizenOver60), weekDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), weekDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), holyDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), holyDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), weekDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), weekDayLateTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), holyDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.CinemaCitizenOver60), holyDayLateTimeCinemaDay, Seq(JPY(1000))),

    (group(CustomerType.General), weekDayEarlyTime, Seq(JPY(1800))),
    (group(CustomerType.General), weekDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.General), holyDayEarlyTime, Seq(JPY(1800))),
    (group(CustomerType.General), holyDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.General), weekDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.General), weekDayLateTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.General), holyDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.General), holyDayLateTimeCinemaDay, Seq(JPY(1100))),

    (group(CustomerType.Senior), weekDayEarlyTime, Seq(JPY(1100))),
    (group(CustomerType.Senior), weekDayLateTime, Seq(JPY(1100))),
    (group(CustomerType.Senior), holyDayEarlyTime, Seq(JPY(1100))),
    (group(CustomerType.Senior), holyDayLateTime, Seq(JPY(1100))),
    (group(CustomerType.Senior), weekDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.Senior), weekDayLateTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.Senior), holyDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.Senior), holyDayLateTimeCinemaDay, Seq(JPY(1100))),

    (group(CustomerType.CollegeStudent), weekDayEarlyTime, Seq(JPY(1500))),
    (group(CustomerType.CollegeStudent), weekDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.CollegeStudent), holyDayEarlyTime, Seq(JPY(1500))),
    (group(CustomerType.CollegeStudent), holyDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.CollegeStudent), weekDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.CollegeStudent), weekDayLateTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.CollegeStudent), holyDayEarlyTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.CollegeStudent), holyDayLateTimeCinemaDay, Seq(JPY(1100))),

    (group(CustomerType.HighSchoolStudent), weekDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), weekDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), holyDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), holyDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), weekDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), weekDayLateTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), holyDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.HighSchoolStudent), holyDayLateTimeCinemaDay, Seq(JPY(1000))),

    (group(CustomerType.Child), weekDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.Child), weekDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.Child), holyDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.Child), holyDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.Child), weekDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Child), weekDayLateTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Child), holyDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Child), holyDayLateTimeCinemaDay, Seq(JPY(1000))),

    (group(CustomerType.Handicapped), weekDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), weekDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), holyDayEarlyTime, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), holyDayLateTime, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), weekDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), weekDayLateTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), holyDayEarlyTimeCinemaDay, Seq(JPY(1000))),
    (group(CustomerType.Handicapped), holyDayLateTimeCinemaDay, Seq(JPY(1000))),

    (group(CustomerType.HandicappedUnder18), weekDayEarlyTime, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), weekDayLateTime, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), holyDayEarlyTime, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), holyDayLateTime, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), weekDayEarlyTimeCinemaDay, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), weekDayLateTimeCinemaDay, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), holyDayEarlyTimeCinemaDay, Seq(JPY(900))),
    (group(CustomerType.HandicappedUnder18), holyDayLateTimeCinemaDay, Seq(JPY(900))),

    (group(CustomerType.MICard), weekDayEarlyTime, Seq(JPY(1600))),
    (group(CustomerType.MICard), weekDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.MICard), holyDayEarlyTime, Seq(JPY(1600))),
    (group(CustomerType.MICard), holyDayLateTime, Seq(JPY(1300))),
    (group(CustomerType.MICard), weekDayEarlyTimeCinemaDay, Seq(JPY(1600))),
    (group(CustomerType.MICard), weekDayLateTimeCinemaDay, Seq(JPY(1300))),
    (group(CustomerType.MICard), holyDayEarlyTimeCinemaDay, Seq(JPY(1600))),
    (group(CustomerType.MICard), holyDayLateTimeCinemaDay, Seq(JPY(1300))),

    (group(CustomerType.ParkingAreaPark80), weekDayEarlyTime, Seq(JPY(1400))),
    (group(CustomerType.ParkingAreaPark80), weekDayLateTime, Seq(JPY(1100))),
    (group(CustomerType.ParkingAreaPark80), holyDayEarlyTime, Seq(JPY(1400))),
    (group(CustomerType.ParkingAreaPark80), holyDayLateTime, Seq(JPY(1100))),
    (group(CustomerType.ParkingAreaPark80), weekDayEarlyTimeCinemaDay, Seq(JPY(1400))),
    (group(CustomerType.ParkingAreaPark80), weekDayLateTimeCinemaDay, Seq(JPY(1100))),
    (group(CustomerType.ParkingAreaPark80), holyDayEarlyTimeCinemaDay, Seq(JPY(1400))),
    (group(CustomerType.ParkingAreaPark80), holyDayLateTimeCinemaDay, Seq(JPY(1100))),
  )

  forAll(tests) { (group, screen, expectedPrices) =>
    s"Tickets (from ${screen.at}) that is bought by $group" should s"be ￥$expectedPrices" in {
      val tickets = BuyTicket(group, screen)
      for ((ticket, expectedPrice) <- tickets.zip(expectedPrices)) {
        assert(ticket.price == expectedPrice)
      }
    }
  }
}
