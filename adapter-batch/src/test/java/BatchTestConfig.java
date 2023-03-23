import de.hartz.software.sodevsalaryguide.BatchConfiguration;
import org.springframework.context.annotation.Import;

@Import({BatchConfiguration.class, PersistenceTestConfig.class})
public class BatchTestConfig {}
