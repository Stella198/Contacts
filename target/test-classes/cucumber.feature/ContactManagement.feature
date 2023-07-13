Feature: Contact Management

  Scenario: Add a new contact
    Given I am on the link "ADD CONTACT"
    When I enter "Jane" in the first name field
    And I enter "Jane" in the last name field
    And I enter "0123456789" in the phone number field
    And I click the submit button
    Then I should see the message "Contact saved successfully!"


  Scenario: Search for a contact
    Given I am on the link "SEARCH CONTACT"
    When I enter "Jane" in the last name field
    And I click the submit button
    Then I should see the message "Contact found"

  Scenario: Delete a contact
    Given I am on the link "DELETE CONTACT"
    When I fill in the contact ID with "3"
    And I click the submit button
    Then I should see the message "Contact deleted successfully!"
