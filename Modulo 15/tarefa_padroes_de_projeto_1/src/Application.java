public class Application {

    public static void main(String[] args) {
        Customer customer = new Customer("A", false);
        Factory factory = getFactory(customer);
        Car car = factory.create(customer.getGradeRequest());
        car.startEngine();

        Customer secondCustomer = new Customer("A", true);
        factory = getFactory(secondCustomer);
        Car secondCar = factory.create(secondCustomer.getGradeRequest());
        secondCar.startEngine();
    }

    private static Factory getFactory(Customer cliente) {
        if (cliente.hasCompanyContract()) {
            return new ContractFactory();
        } else {
            return new ContractLessFactory();
        }
    }
}
