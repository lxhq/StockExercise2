import com.google.inject.AbstractModule;
import model.Repository.Repository;
import model.Repository.RepositoryImpl;

public class RepositoryModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Repository.class).to(RepositoryImpl.class);
    }
}
