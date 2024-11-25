Feature: FitPeo Test Cases

  Scenario: FitPeo Assessmet Scenario
    Given user is on FitPeo Home Page
    And user navigates to Revenue Calculator Page
    And user scroll down the browser until Revenue Calculator slider visible
    When user adjust the slider position to set its value to  "820"
    Then slider should moved and bottom test filed value should be updated to "820"
    When user updates the text field value associated to slider to "560"
    Then slider position should updated and refelctes the value to "560"
    When user updates the text field value associated to slider to "820"
    And user selects check boxes of below CPT codes
      | CPT-99091 | CPT-99453 | CPT-99454 | CPT-99474 |
    Then the Total Recurring Reimbursement for all Patients Per Month shows the value to "$110700"