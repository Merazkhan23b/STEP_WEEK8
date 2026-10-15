
import java.util.*;

abstract class Room {
    int units;

    Room(int units) {
        this.units = units;
    }

    abstract double calculateBill();
}

class SINGLE extends Room {
    SINGLE(int units) {
        super(units);
    }

    @Override
    double calculateBill() {
        return units * 8.0;
    }
}

class SHARED extends Room {
    int occupants;

    SHARED(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    @Override
    double calculateBill() {
        return (units * 6.0) / occupants;
    }
}

class AC extends Room {
    AC(int units) {
        super(units);
    }

    @Override
    double calculateBill() {
        return (units * 10.0) + 200.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Room[] rooms = new Room[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();

            switch (type) {
                case "SINGLE":
                    rooms[i] = new SINGLE(units);
                    break;

                case "SHARED":
                    int occupants = sc.nextInt();
                    rooms[i] = new SHARED(units, occupants);
                    break;

                case "AC":
                    rooms[i] = new AC(units);
                    break;
            }
        }

        for (Room room : rooms) {
            double bill = room.calculateBill();

            System.out.printf("%s: %.2f%n",
                    room.getClass().getSimpleName(), bill);

            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);

        sc.close();
    }
}