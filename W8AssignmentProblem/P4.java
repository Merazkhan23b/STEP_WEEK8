
import java.util.*;

abstract class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract double calculateBonus();
}

class FULLTIME extends Employee {
    FULLTIME(String name, double salary) {
        super(name, salary);
    }

    @Override
    double calculateBonus() {
        return salary * 0.10;
    }
}

class PARTTIME extends Employee {
    PARTTIME(String name, double salary) {
        super(name, salary);
    }

    @Override
    double calculateBonus() {
        return salary * 0.05;
    }
}

class INTERN extends Employee {
    INTERN(String name, double salary) {
        super(name, salary);
    }

    @Override
    double calculateBonus() {
        return 2000.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Employee[] employees = new Employee[n];
        double totalBonus = 0.0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();

            switch (type) {
                case "FULLTIME":
                    employees[i] = new FULLTIME(name, salary);
                    break;

                case "PARTTIME":
                    employees[i] = new PARTTIME(name, salary);
                    break;

                case "INTERN":
                    employees[i] = new INTERN(name, salary);
                    break;
            }
        }

        for (Employee e : employees) {
            double bonus = e.calculateBonus();

            System.out.printf("%s: %.2f%n", e.name, bonus);

            totalBonus += bonus;
        }

        System.out.printf("Total Bonus: %.2f%n", totalBonus);

        sc.close();
    }
}
