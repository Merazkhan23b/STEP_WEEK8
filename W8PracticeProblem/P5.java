
import java.util.*;

abstract class Transport {
    double distance;

    Transport(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();
}

class BUS extends Transport {
    BUS(double distance) {
        super(distance);
    }

    double calculateFare() {
        return Math.min(2 + 0.10 * distance, 10);
    }
}

class TRAIN extends Transport {
    TRAIN(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 3 + 0.15 * distance;
    }
}

class METRO extends Transport {
    double peakHourFactor;

    METRO(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    double calculateFare() {
        return (1.50 + 0.20 * distance) * peakHourFactor;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Transport[] journeys = new Transport[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            switch (type) {
                case "BUS":
                    journeys[i] = new BUS(distance);
                    break;

                case "TRAIN":
                    journeys[i] = new TRAIN(distance);
                    break;

                case "METRO":
                    double factor = sc.nextDouble();
                    journeys[i] = new METRO(distance, factor);
                    break;
            }
        }

        for (Transport t : journeys) {
            double fare = t.calculateFare();

            System.out.printf("%s: %.2f%n",
                    t.getClass().getSimpleName(), fare);

            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);

        sc.close();
    }
}
