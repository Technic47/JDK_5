public record Philosopher(String name) implements Dinner {

    @Override
    public boolean eat(Canteen canteen) throws InterruptedException {
        if (canteen.occupyTable(this)) {
            Thread.sleep(500);
            canteen.freeTable(this);
            return true;
        } else return false;
    }

    public void think() throws InterruptedException {
        Thread.sleep(300);
    }
}
