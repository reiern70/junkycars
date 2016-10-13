package com.junkcars.domain.logic

import com.junkycars.domain.CarAdvert


trait ICarAdvertsLogic {

  def findAll(sort: String): java.util.List[CarAdvert]
}
