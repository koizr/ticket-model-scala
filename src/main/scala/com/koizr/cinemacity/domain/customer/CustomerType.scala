package com.koizr.cinemacity.domain.customer

trait CustomerType

object CustomerType {

  object CinemaCitizen extends CustomerType

  object CinemaCitizenOver60 extends CustomerType

  object General extends CustomerType

  object Senior extends CustomerType

  object CollegeStudent extends CustomerType

  object HighSchoolStudent extends CustomerType

  object Child extends CustomerType

  object Handicapped extends CustomerType

  object HandicappedPartner extends CustomerType

  object HandicappedUnder18 extends CustomerType

  object HandicappedUnder18Partner extends CustomerType

  object MICard extends CustomerType

  object ParkingAreaPark80 extends CustomerType

}
