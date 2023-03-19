import de.hartz.software.sodevsalaryguide.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

@Import(value = PersistenceConfiguration.class)
public class PersistenceTestConfig {

   public static void main(String... args) {
       SpringApplication.run(PersistenceTestConfig.class, args);
   }
}