

import java.util.*;

abstract class Payment {
    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract double calculateAmount();

    void display() {
        double adjustedAmount = calculateAmount();
        System.out.printf("%s: %.2f%n",
                getClass().getSimpleName(), adjustedAmount);
    }
}

class CARD extends Payment {
    CARD(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 1.02;
    }
}

class WALLET extends Payment {
    WALLET(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 1.01;
    }
}

class BANKTRANSFER extends Payment {
    BANKTRANSFER(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Payment[] payments = new Payment[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            switch (type) {
                case "CARD":
                    payments[i] = new CARD(amount);
                    break;
                case "WALLET":
                    payments[i] = new WALLET(amount);
                    break;
                case "BANKTRANSFER":
                    payments[i] = new BANKTRANSFER(amount);
                    break;
            }
        }

        for (Payment p : payments) {
            p.display();
            total += p.calculateAmount();
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}