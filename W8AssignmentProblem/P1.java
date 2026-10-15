
import java.util.*;

abstract class Customer {
    double amount;

    Customer(double amount) {
        this.amount = amount;
    }

    abstract double calculateAmount();
}

class STUDENT extends Customer {
    STUDENT(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 0.90;
    }
}

class STAFF extends Customer {
    STAFF(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount * 0.95;
    }
}

class GUEST extends Customer {
    GUEST(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount + 10;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Customer[] bills = new Customer[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            switch (type) {
                case "STUDENT":
                    bills[i] = new STUDENT(amount);
                    break;

                case "STAFF":
                    bills[i] = new STAFF(amount);
                    break;

                case "GUEST":
                    bills[i] = new GUEST(amount);
                    break;
            }
        }

        for (Customer c : bills) {
            double finalAmount = c.calculateAmount();

            System.out.printf("%s: %.2f%n",
                    c.getClass().getSimpleName(), finalAmount);

            total += finalAmount;
        }

        System.out.printf("Total: %.2f%n", total);

        sc.close();
    }
}
