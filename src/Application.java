
public class Application  {
    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    private void run() {
        IDraw idraw = new IDraw();
        idraw.run();
    }
}
