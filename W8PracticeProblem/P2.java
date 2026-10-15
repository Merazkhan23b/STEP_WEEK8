
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

abstract class LibraryItem {
    String title;

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int getBorrowingDays();

    void displayDueDate(LocalDate currentDate) {
        LocalDate dueDate = currentDate.plusDays(getBorrowingDays());

        System.out.println(title + ": " +
            dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}

class BOOK extends LibraryItem {
    BOOK(String title) {
        super(title);
    }

    int getBorrowingDays() {
        return 14;
    }
}

class DVD extends LibraryItem {
    DVD(String title) {
        super(title);
    }

    int getBorrowingDays() {
        return 7;
    }
}

class MAGAZINE extends LibraryItem {
    MAGAZINE(String title) {
        super(title);
    }

    int getBorrowingDays() {
        return 3;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        LibraryItem[] items = new LibraryItem[n];

        LocalDate currentDate = LocalDate.of(2023, 10, 26);

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("\\s+", 2);

            String type = parts[0];
            String title = parts[1].replaceAll("^\"|\"$", "");

            switch (type) {
                case "BOOK":
                    items[i] = new BOOK(title);
                    break;
                case "DVD":
                    items[i] = new DVD(title);
                    break;
                case "MAGAZINE":
                    items[i] = new MAGAZINE(title);
                    break;
            }
        }

        for (LibraryItem item : items) {
            item.displayDueDate(currentDate);
        }

        sc.close();
    }
}