package policies;

public class BasicIncomePolicy implements IDriverIncomePolicy {

    public static final Double DEDUCTION_PERCENTAGE = 0.3;

    @Override
    public Double calculateDriverIncome(Double totalTripPrice) {
        return totalTripPrice -  (totalTripPrice * DEDUCTION_PERCENTAGE);
    }
}
