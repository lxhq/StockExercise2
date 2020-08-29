import com.google.inject.AbstractModule;
import model.repository.Repository;
import model.repository.RepositoryImpl;

public class RepositoryModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Repository.class).to(RepositoryImpl.class);
    }
}
