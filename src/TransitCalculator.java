import java.util.Scanner;

public class TransitCalculator {

    protected int days;
    protected int numRides;
    protected int age;
    protected static final double[] RIDE_FARES = {2.75, 33.00, 127.00};


    // Constructor with the parameters to set the values for days and numRides
    public TransitCalculator(int days, int numRides, int age) {
        this.days = days;
        this.numRides = numRides;
        this.age = age;

    }

    public double unlimited7Price() {
        // Calculate the number of 7-day unlimited passes needed
        int numUnlimitedRides = (int) Math.ceil((double) days / 7);
        // Calculate the total cost of the passes
        double totalCost = numUnlimitedRides * RIDE_FARES[1];
        // Calculate the price per-ride
        return totalCost / numRides;
    }

    public double[] getRidePrices() {

        double[] ridePrices = new double[3];

        // Stores the price for the unlimited 7-day fare option using the unlimited7Price method
        ridePrices[0] = unlimited7Price();
        // Sets the price per ride for the single-ride option directly from the RIDE_FARES array
        ridePrices[1] = RIDE_FARES[0];
        // Calculates the price per-ride for the 30-day Unlimited option
        ridePrices[2] = RIDE_FARES[2] / numRides;

        // Conditional to provide reduced fare if rider is >= 65

        if (age >= 65) {
            ridePrices[0] = 1.35;
            ridePrices[1] = 16.50;
            ridePrices[2] = 63.50;
        }

        return ridePrices;

    }

    public String getBestFare() {

        int winningIndex = 0;
        double[] ridePrices = getRidePrices();
        String[] fareOptions = {"Pay-per-ride", "7-day Unlimited Rides", "30-day Unlimited Rides"};

        for (int i = 0; i < RIDE_FARES.length; i++) {
            if (ridePrices[i] < ridePrices[winningIndex]) {
                winningIndex = i;
            }

        }
        return "You should get the " + fareOptions[winningIndex] + " option at $" + ridePrices[winningIndex] + " per ride.";


    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Get the number of days the user would like their ticket for

        System.out.println("Enter the amount of days you would like a ticket for.");

        int nunberOfDays = scnr.nextInt();

        // Get the number of rides that the user would like

        System.out.println("And how many rides would you like?");

        int numberOfRides = scnr.nextInt();

        System.out.println("And how old are you?");

        int ridersAge = scnr.nextInt();

        TransitCalculator nyFare = new TransitCalculator(nunberOfDays, numberOfRides, ridersAge);

        System.out.println(nyFare.getBestFare());

        scnr.close();


    }


}