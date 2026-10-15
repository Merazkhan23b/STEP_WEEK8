
import java.util.*;
import java.time.LocalDate;

abstract class Subscription {
    String name;
    LocalDate startDate;

    Subscription(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    abstract int getValidityDays();

    LocalDate calculateRenewalDate() {
        return startDate.plusDays(getValidityDays());
    }
}

class BASIC extends Subscription {
    BASIC(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    int getValidityDays() {
        return 30;
    }
}

class STANDARD extends Subscription {
    STANDARD(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    int getValidityDays() {
        return 90;
    }
}

class PREMIUM extends Subscription {
    PREMIUM(String name, LocalDate startDate) {
        super(name, startDate);
    }

    @Override
    int getValidityDays() {
        return 365;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Subscription[] subscribers = new Subscription[n];

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate startDate = LocalDate.parse(sc.next());

            switch (type) {
                case "BASIC":
                    subscribers[i] = new BASIC(name, startDate);
                    break;

                case "STANDARD":
                    subscribers[i] = new STANDARD(name, startDate);
                    break;

                case "PREMIUM":
                    subscribers[i] = new PREMIUM(name, startDate);
                    break;
            }
        }

        for (Subscription s : subscribers) {
            System.out.println(
                s.name + ": " + s.calculateRenewalDate()
            );
        }

        sc.close();
    }
}
