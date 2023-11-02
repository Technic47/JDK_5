import java.util.Arrays;
import java.util.List;

public class Main {
    private static Canteen canteen;

    public static void main(String[] args) {
        canteen = new Canteen(2);

        List<Philosopher> list = Arrays.asList(
                new Philosopher("Ivan"),
                new Philosopher("Andrey"),
                new Philosopher("Sergey"),
                new Philosopher("Ilya"),
                new Philosopher("Denis")
        );

        list.forEach(p -> {
            Thread th = new Thread(createThread(p));
            th.start();
        });
    }

    private static Runnable createThread(Philosopher philosopher) {
        return  () -> {
            try {
                for (int i = 0; i < 3; i++) {
                    boolean done = false;
                    while (!done) {
                        try {
                            done = philosopher.eat(canteen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    philosopher.think();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
