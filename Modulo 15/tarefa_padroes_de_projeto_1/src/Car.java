public class Car {

    private int horsePower;
    private Long fuelQuantity;
    private String color;

    public Car(int horsePower, Long fuelQuantity, String color) {
        this.horsePower = horsePower;
        this.fuelQuantity = fuelQuantity;
        this.color = color;
    }

    public void startEngine(){
        System.out.println("Starting engine... " + getClass().getSimpleName());
        System.out.println("The car have " + fuelQuantity + " fuel and engine has been started, and is ready to utilize " + horsePower + " horsepowers." );
    }
    public void clean(){
        System.out.println("Car has been cleaned, and the " + color.toLowerCase() + " color shines!");
    }
    public void mechanicCheck(){
        System.out.println("Car has been checked by the mechanic. Everything looks good!");
    }
    public void fuelCar(Long fuelQuantity){
        this.fuelQuantity += fuelQuantity;
        System.out.println("Car is being filled with " + fuelQuantity + "l of gas.");
    }
}
