Feature: Register User.

	@register
  Scenario: Successful registration
    Given I am a new user
    When I register my data correctly in the page for a new user
    Then I should get a welcome message