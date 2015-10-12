package sample;

import sample.System.*;

public class Controller {

    public void initialize() {
        //Server s = new Server ("Analis2");
        new Client();
        System.out.println("Инициализация закончена");
    }
}
