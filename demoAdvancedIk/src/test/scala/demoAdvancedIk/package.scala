package demoAdvancedIk

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.text.SimpleDateFormat
import java.util.Date

package object requests {

  val headers_0 = Map(
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "sec-ch-ua" -> """ Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "macOS")

  val headers_3 = Map(
    "Cache-Control" -> "no-cache",
    "Origin" -> "http://localhost:8081",
    "Pragma" -> "no-cache",
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "same-origin",
    "Sec-Fetch-User" -> "?1",
    "sec-ch-ua" -> """ Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-ch-ua-platform" -> "macOS")

  object HomePage {
    val openHomePage = exec(http("Open Home Page")
      .get("/")
      .headers(headers_0))
  }

  object Owner {
    val getFindOwnersPage = exec(http("Get Find Owners Page")
      .get("/owners/find")
      .headers(headers_0))

    val getAddOwnerPage = exec(http("GET Add Owner")
      .get("/owners/new")
      .headers(headers_0))

    val postAddOwner = exec(http("POST Add Owner")
      .post("/owners/new")
      .headers(headers_3)
      .formParam("firstName", "Iryna")
      .formParam("lastName", "Kozak3")
      .formParam("address", "27 Merry Lane, NP000254820")
      .formParam("city", "East Hanover")
      .formParam("telephone", "9739476111")
      .resources(http("Get Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(302)))

    val getFindOwnersByLastname = exec(http("GET Find Owners by Lastname")
      .get("/owners?lastName=Kozak3")
      .headers(headers_0)
      .resources(http("GET Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(200)))

    val getEditOwnerPage = exec(http("GET Open Edit Owner Page")
      .get("/owners/15/edit")
      .headers(headers_0))

    val postUpdateOwner = exec(http("POST Update Owner")
      .post("/owners/15/edit")
      .headers(headers_3)
      .formParam("firstName", "Iryna")
      .formParam("lastName", "Kozak3")
      .formParam("address", "27 Merry Lane, NP000254820")
      .formParam("city", "East Hanover")
      .formParam("telephone", "9739476113")
      .resources(http("GET Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(302)))
  }

  object Pet {
    val getAddPet = exec(http("GET Add Pet")
      .get("/owners/15/pets/new")
      .headers(headers_0))

    val postAddPet = exec(http("POST Add Pet")
      .post("/owners/15/pets/new")
      .headers(headers_3)
      .formParam("id", "")
      .formParam("name", "Dog")
      .formParam("birthDate", "2021/04/02")
      .formParam("type", "dog")
      .resources(http("Get Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(200)))

    val getEditPetPage = exec(http("GET Edit Pet Page")
      .get("/owners/15/pets/19/edit")
      .headers(headers_0))

    val postEditPetName = exec(http("POST Edit Pet Name")
      .post("/owners/15/pets/19/edit")
      .headers(headers_3)
      .formParam("id", "19")
      .formParam("name", "Dog")
      .formParam("birthDate", "2021/04/01")
      .formParam("type", "dog")
      .resources(http("GET Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(302)))
  }

  object Visit {
    val getAddNewVisitPage = exec(http("GET Add new visit")
      .get("/owners/15/pets/19/visits/new")
      .headers(headers_0))

    val postAddNewVisit = exec(http("POST Add new visit")
      .post("/owners/15/pets/19/visits/new")
      .headers(headers_3)
      .formParam("date", getTimeFormat())
      .formParam("description", "dental surgey")
      .formParam("petId", "19")
      .resources(http("GET Owners Info")
        .get("/owners/15")
        .headers(headers_0))
      .check(status.is(302)))
  }

  object Vets {

    val getVets = exec(http("GET Vets List")
      .get("/vets")
      .headers(headers_0))
  }

  def getTimeFormat() = {
    new SimpleDateFormat("yyyy/MM/dd").format(new Date).toString
  }
}
