import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public void bookSeat(String name) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(name + " successfully booked a seat.");
                availableSeats--;
            } else {
                System.out.println(name + " failed to book a seat. No seats available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String name;

    public BookingThread(TicketBookingSystem system, String name, int priority) {
        this.system = system;
        this.name = name;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(name);
    }
}

public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        BookingThread vip1 = new BookingThread(system, "VIP1", Thread.MAX_PRIORITY);
        BookingThread vip2 = new BookingThread(system, "VIP2", Thread.MAX_PRIORITY);
        BookingThread user1 = new BookingThread(system, "User1", Thread.NORM_PRIORITY);
        BookingThread user2 = new BookingThread(system, "User2", Thread.NORM_PRIORITY);
        BookingThread user3 = new BookingThread(system, "User3", Thread.NORM_PRIORITY);
        BookingThread user4 = new BookingThread(system, "User4", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}
