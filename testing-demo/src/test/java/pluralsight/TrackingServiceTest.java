package pluralsight;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 */
public class TrackingServiceTest {

    private TrackingService service;

    @BeforeClass
    public static void before() {
        //System.out.println("Before Class");
    }

    @AfterClass
    public static void after() {
        //System.out.println("After Class");
    }

    @Before
    public void setUp() {
        //System.out.println("Before");
        service = new TrackingService(new NotifierStub());
    }

    @After
    public void tearDown() {
        //System.out.println("After");
    }

    @Test
    @Category({GoodTestsCategory.class, BadTestsCategory.class})
    public void newTrackingServiceTotalIsZero() {
        assertEquals("Tracking service total was not zero", 0, service.getTotal());
    }

    @Test
    @Category(GoodTestsCategory.class)
    public void whenAddingProteinTotalIncreasesByThatAmount() {
        service.addProtein(10);
        //assertEquals("Protein amount was not correct", 10, service.getTotal());
        //assertEquals(10, service.getTotal());
        //assertThat(service.getTotal(), is(10));
        assertThat(service.getTotal(), allOf(is(10), instanceOf(Integer.class)));
    }

    @Test
    @Category(GoodTestsCategory.class)
    public void whenRemovingProteinTotalRemainsZero() {
        service.removeProtein(5);
        assertEquals(0, service.getTotal());
    }

    @Test
    public void whenGoalIsMetHistoryIsUpdated() throws Exception {
        final Notifier mockNotifier = mock(Notifier.class);
        service = new TrackingService(mockNotifier);

        when(mockNotifier.send("goal met")).thenReturn(true);

        service.setGoal(5);
        service.addProtein(6);

        // Stub
        HistoryItem result = service.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //@Test(expected = InvalidGoalException.class)  // Rule使うときは外す。
    @Test
    public void whenGoalIsSetLessThanZeroThenExceptionIsThrown() throws Exception {
        thrown.expect(InvalidGoalException.class);
        thrown.expectMessage("Goal");
        thrown.expectMessage(containsString("Goal"));
        service.setGoal(-10);
    }

    @Test(timeout = 200)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(100);
    }
}
