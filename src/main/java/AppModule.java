import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        //bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
    }
}