package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Петр", "Петров", "petrov@mail.ru", new Car("Volvo", 40)));
      userService.add(new User("Саша", "Козлов", "kozlov@mail.ru", new Car("BMW", 200)));
      userService.add(new User("Миша", "Лазарев", "user3@mail.ru", new Car("Ford", 3)));
      userService.add(new User("Ольга", "Фролова", "frolova@mail.ru", new Car("Lada", 2110)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Ford", 3));

      context.close();
   }
}
