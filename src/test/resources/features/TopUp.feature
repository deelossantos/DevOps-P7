Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 110

  Scenario: Add money to Revolut account using bank account
    Given Danny has 99 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 199

  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 250

  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The balance in his euro account should be <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

  Example: Payment service rejects the request
    Given Danny has 125 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    And  Selected payment service is not active
    When Danny tops up
    Then The new balance of his euro account should now be 125

  Example: Payment service accepts the request
    Given Danny has 125 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    When Danny tops up
    Then The new balance of his euro account should now be 225

  Rule: Sending money to a friend using Revolut shouldn't complete if the sender account has not enough balance

  Example: Sending money to a friend using Revolut.
    Given Danny has 125 euro in his euro Revolut account
    And Peter has 500 euro in his euro Revolut account
    When Danny sends 100 euro to Peter to his euro Revolut account
    Then The new balance for Danny account should now be 25
    And The new balance for Peter account should now be 600

  Example: Sending money to a friend using Revolut, not enough funds.
    Given Danny has 125 euro in his euro Revolut account
    And Peter has 500 euro in his euro Revolut account
    When Danny sends 126 euro to Peter to his euro Revolut account
    Then The new balance for Danny account should now be 125
    And The new balance for Peter account should now be 500