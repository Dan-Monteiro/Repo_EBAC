public class ContractLessFactory extends Factory {
    @Override
    Car retrieveCar(String requestedGrade) {
        if ("A".equals(requestedGrade)) {
            return new Beatle(100, 90L, "bege");
        } else {
            return null;
        }
    }
}
