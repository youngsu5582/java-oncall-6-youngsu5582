package oncall;

import oncall.controller.GameController;
import oncall.service.SpecialDayService;

public class Application {
    public static void main(String[] args) {
        SpecialDayService specialDayService = new SpecialDayService();
        specialDayService.process();

        GameController gameController = new GameController();
        gameController.service();
    }
}
