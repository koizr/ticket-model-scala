package com.koizr.cinemacity.domain.screen

import com.koizr.cinemacity.domain.datetime.{DateTime, Time}

case class Screen(film: Film, screenNumber: ScreenNumber, at: DateTime)

case class ScreenNumber(number: Int)

case class Film(title: String, time: Time)

