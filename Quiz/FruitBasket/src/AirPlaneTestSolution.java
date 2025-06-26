import org.junit.Test;

public class AirplaneTestSolution {

    @Test
    public void testBoardPassengersSuccessfully() {
        Airplane airplane = new Airplane("Boeing", 100, 50);
        boolean result = airplane.boardPassengers(10);

        Assert.assertTrue(result);
        Assert.assertEquals(60, airplane.getPassangersOnBoard());
    }

    @Test
    public void testDisembarkPassengersUnsuccessfully() {
        Airplane airplane = new Airplane("Airbus", 100, 20);
        boolean result = airplane.disembarkPassengers(25);

        Assert.assertFalse(result);
        Assert.assertEquals(20, airplane.getPassangersOnBoard());
    }
}
