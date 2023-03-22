import de.hartz.software.sodevsalaryguide.PersistenceConfiguration;
import org.springframework.context.annotation.Import;

@Import(value = PersistenceConfiguration.class)
public class PersistenceTestConfig {}
