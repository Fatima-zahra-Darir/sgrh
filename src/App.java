import views.LoginView;

public class App {

    // Pour admin : username  : admin _ password:admin123;
    // Pour admin : username : son  email user par exemple (abdel@)   _  password:  son id parc exemple de (abdel@) : 24;

    public static void main(String[] args) throws Exception {
        LoginView login = new LoginView();
        login.setVisible(true);
    }
}
