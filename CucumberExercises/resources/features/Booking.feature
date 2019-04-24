Feature: Booking a trip

	@booking
  Scenario: Successful booking
    Given I am an user to book a first class round trip for 3 passengers on December/19 in Unified Airlines
		And Departing from Paris
		And Arriving to Acapulco
		When I register my data to book a trip correctly in the page
		Then I should get confirmation message for my trip
