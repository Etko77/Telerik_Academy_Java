import java.util.ArrayList;
import java.util.List;

public class Airplane {
    private String name;
    private final int MAX_LENGTH_NAME = 10;
    private final int MIN_LENGTH_NAME = 3;
    private int maxCapacity;
    private int passangersOnBoard;


    public boolean boardPassengers(int passengers) {
        if (passengers<= 0) return false;
        if (getPassangersOnBoard() + passengers > getMaxCapacity()) return false;

        setPassangersOnBoard(getPassangersOnBoard()+passengers);
        return true;
    }
    public boolean disembarkPassengers(int passengers) {
        if (passengers<= 0 || passengers> getPassangersOnBoard()) return false;

        setPassangersOnBoard(getPassangersOnBoard()-passengers);
        return true;
    }
}

    public Airplane(String name, int maxCapacity, int passangersOnBoard) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.passangersOnBoard = passangersOnBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getPassangersOnBoard() {
        return passangersOnBoard;
    }

    public void setPassangersOnBoard(int passangerOnBoard) {
        this.passangersOnBoard = passangerOnBoard;
    }
}
