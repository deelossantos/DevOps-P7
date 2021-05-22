package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    Person peter;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        peter = new Person("Peter");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        double newAccountBalance = danny.getAccountBalance("EUR");
        System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Peter has {double} euro in his euro Revolut account")
    public void peterHasEuroInHisEuroRevolutAccount(double startingBalance) {
        System.out.println("Peter has starting account balance of: " + String.valueOf(startingBalance));
        peter.setAccountBalance(startingBalance);
        double newAccountBalance = peter.getAccountBalance("EUR");
        System.out.println("Peter's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        if (topUpMethod.getStatus()){
            danny.getAccount("EUR").addFunds(topUpAmount);
            System.out.println("selected payment service " + topUpMethod.getType() + " Accepted the request" );
        }
        else {System.out.println("selected payment service " + topUpMethod.getType() + " Rejected the request" );}
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Then("The new balance for Danny account should now be {double}")
    public void the_new_balance_for_danny_account_should_now_be(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Then("The new balance for Peter account should now be {double}")
    public void the_new_balance_for_peter_account_should_now_be(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = peter.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {int}")
    public void danny_has_a_starting_balance_of(Integer int1) {
        danny.setAccountBalance(int1);
        double newAccountBalance = danny.getAccountBalance("EUR");
        System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @When("Danny now tops up by {int}")
    public void danny_now_tops_up_by(Integer int1) {
        danny.getAccount("EUR").addFunds(int1);
    }

    @Then("The balance in his euro account should be {int}")
    public void the_balance_in_his_euro_account_should_be(Integer int1) {
        //Arrange
        double expectedResult = int1;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Selected payment service is not active")
    public void selected_payment_service_is_not_active() {
        topUpMethod.setStatus(false);
    }

    @When("Danny sends {int} euro to Peter to his euro Revolut account")
    public void danny_sends_euro_to_peter_to_his_euro_revolut_account(Integer int1) {
        danny.getAccount("EUR").sendFunds(int1, peter.getAccount("EUR"));
    }

}
