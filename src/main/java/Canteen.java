import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Canteen implements Tables {
    private final Philosopher[] tables;
    private static int tablesCount = 0;
    private volatile Integer size = 0;

    public Canteen(int count) {
        tablesCount = count;
        this.tables = new Philosopher[tablesCount];
    }

    @Override
    public boolean occupyTable(Philosopher philosopher) {
        if (size == tablesCount) {
            return false;
        }
        for (int i = 0; i < tables.length; i++) {
            if (tables[i] == null) {
                tables[i] = philosopher;
                size++;
                System.out.println(Thread.currentThread().getName() + " "
                        + philosopher.name() + " occupied table. " +
                        "Occupied tables - " + size +
                        Arrays.toString(tables));
                return true;
            }
        }
        return false;
    }

    @Override
    public void freeTable(Philosopher philosopher) {
        for (int i = 0; i < tables.length; i++) {
            if (tables[i] != null && tables[i].equals(philosopher)) {
                tables[i] = null;
                size--;
                System.out.println(Thread.currentThread().getName() + " "
                        + philosopher.name() + " left table " +
                        "Occupied tables - " + size);
            }
        }
    }
}
