// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.main;

/**
 * Интерфейс-демонстратор для тестирования функциональности стрелка.
 * Содержит статический метод для запуска демонстрации.
 *
 * @author Решетникова
 * @version 1.0
 */
public interface ShooterDemo {
  /**
   * Запускает демонстрацию работы со стрелком.
   * Выводит информационное сообщение и делегирует вызов
   * статическому методу demonstrate() класса Shooter.
   *
   * @see ru.reshetnikova.weapon.Shooter#demonstrate()
   */
  public static void demonstrateShooterMethods() {
    System.out.println("Демонстрация работы с Shooter через вызов его методов");
    ru.reshetnikova.weapon.Shooter.demonstrate();
  }
}