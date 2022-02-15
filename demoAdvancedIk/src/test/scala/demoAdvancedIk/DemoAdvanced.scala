package demoAdvancedIk

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import requests._

class DemoAdvanced extends Simulation {

	val th_min = 1
	val th_max = 2
	val test_duration = System.getProperty("duration", "60").toInt
	val test_users = System.getProperty("users", "1").toInt

	val httpProtocol = http
		.baseUrl("http://localhost:8081")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,it;q=0.6,uk;q=0.5")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36")
	  .disableFollowRedirect

	val newVisit = exec(Owner.getFindOwnersPage)
		.pause(th_min, th_max)
		.exec(Owner.getAddOwnerPage)
		.pause(th_min, th_max)
		.exec(Owner.postAddOwner)
		.pause(th_min, th_max)
		.exec(Pet.getAddPet)
		.pause(th_min, th_max)
		.exec(Pet.postAddPet)
		.pause(th_min, th_max)
		.exec(Pet.getEditPetPage)
		.pause(th_min, th_max)
		.exec(Pet.postEditPetName)
		.pause(th_min, th_max)
		.exec(Visit.getAddNewVisitPage)
		.pause(th_min, th_max)
		.exec(Visit.postAddNewVisit)
		.pause(th_min, th_max)

	val consultation = exec(Owner.getFindOwnersPage)
		.pause(th_min, th_max)
		.exec(Owner.getFindOwnersByLastname)
		.pause(th_min, th_max)
		.exec(Owner.getEditOwnerPage)
		.pause(th_min, th_max)
		.exec(Owner.postUpdateOwner)
		.pause(th_min, th_max)
		.exec(Vets.getVets)
		.pause(th_min, th_max)

	val scn = scenario("DemoAdvanced")
	  .during(test_duration.seconds, exitASAP = false) {
			exec(HomePage.openHomePage).pause(th_min,th_max)
				.randomSwitch(
					80.0 -> newVisit,
					20.0 -> consultation
				)
		}

	setUp(scn.inject(
		rampConcurrentUsers(5).to(test_users).during(test_duration)
	)).protocols(httpProtocol)
}