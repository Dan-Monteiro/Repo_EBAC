import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ClienteDAOTest.class,
    ClienteServiceTest.class
})
public class AllTests {
}
