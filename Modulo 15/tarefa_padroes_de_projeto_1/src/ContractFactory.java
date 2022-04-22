public class ContractFactory extends Factory {

        @Override
        Car retrieveCar(String requestedGrade) {
            if ("A".equals(requestedGrade)) {
                return new Jeep(290, 100L, "prata");
            } else {
                return null;
            }
        }
}
