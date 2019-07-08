import java.util.ArrayList;
import java.util.List;

public class ConfigurationFactory {

    static List<Configuration> getConfigurations() {
        Configuration bookConfiguration = ConfigurationFactory.getBookConfiguration();
        Configuration personConfiguration = ConfigurationFactory.getPersonConfiguration();

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(bookConfiguration);
        configurations.add(personConfiguration);
        return configurations;
    }

    private static Configuration getBookConfiguration() {
        Configuration bookConfiguration = new Configuration();
        bookConfiguration.setUseCase("Book Objects");
        bookConfiguration.setSamplesize(50);
        bookConfiguration.setObjects(1000);
        bookConfiguration.setPojo(createBook());
        return bookConfiguration;
    }

    private static Configuration getPersonConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setUseCase("Person Objects");
        configuration.setSamplesize(15);
        configuration.setObjects(20_000);
        configuration.setPojo(createPerson());
        return configuration;
    }

    private static Book createBook() {
        Book book = new Book();
        book.setIsbn(123);
        book.setTitle("ABC");
        book.setAuthor(createPerson());
        book.setDescription("asdfasdf");
        return book;
    }

    private static Person createPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Name");
        person.setAge(30);
        return person;
    }

}
