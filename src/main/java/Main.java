import org.hibernate.SessionFactory;
import repositories.SessionFactorySingleton;

public class Main {
    public static final SessionFactory factory = SessionFactorySingleton.getInstance();

    public static void main(String[] args) {
        factory.openSession();
        factory.close();
    }
}
