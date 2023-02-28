package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User petr = new User("Petr", "Nikolaev", "petrn@mail.ru" );
      User ivan = new User("Ivan", "Toporov", "topor@mail.ru" );
      User anna = new User("Anna", "Petrova", "anna@mail.ru" );
      User ulya = new User("Ulya", "Ismagilova", "ismag@mail.ru" );

      Car lada = new Car("Lada", 9);
      Car nissan = new Car("Nissan", 360);
      Car reno = new Car("Renault", 3);
      Car bmw = new Car("BMW", 5);

      userService.add(petr.setCar(lada).setUser(petr));
      userService.add(ivan.setCar(nissan).setUser(ivan));
      userService.add(anna.setCar(reno).setUser(anna));
      userService.add(ulya.setCar(bmw).setUser(ulya));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Lada", 9));

      try {
         User notFoundUser = userService.getUserByCar("KAMAZ", 500);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
