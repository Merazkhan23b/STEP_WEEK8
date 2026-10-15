
import java.util.*;

abstract class Delivery {
    double weight, distance;

    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract double calculateFee();

    void display() {
        System.out.printf("%s: %.2f%n",
                getClass().getSimpleName(), calculateFee());
    }
}

class STANDARD extends Delivery {
    STANDARD(double weight, double distance) {
        super(weight, distance);
    }

    double calculateFee() {
        return 5 + (0.50 * weight) + (0.10 * distance);
    }
}

class EXPRESS extends Delivery {
    EXPRESS(double weight, double distance) {
        super(weight, distance);
    }

    double calculateFee() {
        return 15 + (1.00 * weight) + (0.20 * distance);
    }
}

class INTERNATIONAL extends Delivery {
    double customsFee;

    INTERNATIONAL(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    double calculateFee() {
        return 25 + (2.00 * weight) + (0.50 * distance)
                + customsFee;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Delivery[] deliveries = new Delivery[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double distance = sc.nextDouble();

            switch (type) {
                case "STANDARD":
                    deliveries[i] = new STANDARD(weight, distance);
                    break;

                case "EXPRESS":
                    deliveries[i] = new EXPRESS(weight, distance);
                    break;

                case "INTERNATIONAL":
                    double customsFee = sc.nextDouble();
                    deliveries[i] = new INTERNATIONAL(
                            weight, distance, customsFee);
                    break;
            }
        }

        for (Delivery d : deliveries) {
            d.display();
            total += d.calculateFee();
        }

        System.out.printf("Total: %.2f%n", total);

        sc.close();
    }
}
