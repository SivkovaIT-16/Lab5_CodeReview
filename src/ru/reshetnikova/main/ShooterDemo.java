// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.main;

public interface ShooterDemo {
  public static void demonstrateShooterMethods() {
    System.out.println("Демонстрация работы с Shooter через вызов его методов");
    ru.reshetnikova.weapon.Shooter.demonstrate();
  }
}