// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг бинарных операторов и оператора присваивания
// FIX_ME: удалены лишние import
package ru.reshetnikova.power;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 * Класс для вычисления степени числа.
 * Содержит утилитарные методы для возведения числа в степень.
 *
 * @author Решетникова
 * @version 1.0
 */
public class PowerCalculator {
  /**
   * Вычисляет число X, возведенное в степень Y.
   *
   * @param xStr строковое представление основания степени
   * @param yStr строковое представление показателя степени
   * @return результат возведения в степень
   * @throws NumberFormatException если строки не содержат корректных целых чисел
   */
  public static double calculatePower(String xStr, String yStr) {
    // Преобразуем строки в числа используя короткое имя метода
    int x = parseInt(xStr);
    int y = parseInt(yStr);

    // Возводим в степень используя короткое имя метода
    double result = Math.pow(x, y);

    return result;
  }

  /**
   * Демонстрационный метод.
   * Запрашивает у пользователя число и степень, выводит результат вычисления.
   * Обрабатывает ошибки ввода некорректных значений.
   */
  public static void demonstrate() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите два числа:");

    System.out.print("Введите число X: ");
    String xStr = scanner.nextLine();

    System.out.print("Введите степень Y: ");
    String yStr = scanner.nextLine();

    try {
      double result = calculatePower(xStr, yStr);
      System.out.println(xStr + " ^ " + yStr + " = " + result);
    } catch (NumberFormatException e) {
      System.out.println("Ошибка: введите целые числа!");
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

  }
}
