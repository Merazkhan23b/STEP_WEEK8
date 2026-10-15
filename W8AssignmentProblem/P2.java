
import java.util.*;

abstract class Vehicle {
    int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract double calculateCharge();
}

class BIKE extends Vehicle {
    BIKE(int hours) {
        super(hours);
    }

    @Override
    double calculateCharge() {
        return hours * 10.0;
    }
}

class CAR extends Vehicle {
    CAR(int hours) {
        super(hours);
    }

    @Override
    double calculateCharge() {
        return 30 + (hours - 1) * 20.0;
    }
}

class TRUCK extends Vehicle {
    TRUCK(int hours) {
        super(hours);
    }

    @Override
    double calculateCharge() {
        return Math.max(hours * 50.0, 100.0);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Vehicle[] vehicles = new Vehicle[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();

            switch (type) {
                case "BIKE":
                    vehicles[i] = new BIKE(hours);
                    break;

                case "CAR":
                    vehicles[i] = new CAR(hours);
                    break;

                case "TRUCK":
                    vehicles[i] = new TRUCK(hours);
                    break;
            }
        }

        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();

            System.out.printf("%s: %.2f%n",
                    v.getClass().getSimpleName(), charge);

            total += charge;
        }

        System.out.printf("Total: %.2f%n", total);

        sc.close();
    }
}
