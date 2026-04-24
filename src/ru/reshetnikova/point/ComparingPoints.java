// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг бинарных операторов и оператора присваивания
package ru.reshetnikova.point;

import ru.reshetnikova.validation.Validation;

import java.util.Scanner;

/**
 * Класс, представляющий точку на плоскости с координатами (x, y).
 *
 * <p>Особенности:</p>
 * <ul>
 *   <li>Координаты проходят валидацию при создании и изменении</li>
 *   <li>Реализован метод equals для сравнения точек</li>
 *   <li>Имеет удобное строковое представление</li>
 * </ul>
 *
 * @author Решетникова
 * @version 1.0
 */
public class ComparingPoints {
  // FIX_ME: имена полей переименованы, должны начинаться с меленькой буквы
  //private int X;
  //private int Y;
  private int x;
  private int y;

  /**
   * Конструктор точки.
   *
   * @param x координата X
   * @param y координата Y
   */
  public ComparingPoints(int x, int y) {
    Validation.validateCoordinates(x, y);
    this.x = x;
    this.y = y;
  }

  /**
   * Сравнивает текущую точку с другим объектом.
   * Две точки считаются равными, если их координаты X и Y совпадают.
   *
   * @param obj объект для сравнения
   * @return true если точки равны, иначе false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ComparingPoints point = (ComparingPoints) obj;
    return x == point.x && y == point.y;
  }

  /**
   * Возвращает строковое представление точки.
   *
   * @return строка вида "Точка(x;y)"
   */
  @Override
  public String toString() {
    return "Точка(" + x + ";" + y + ")";
  }

  /**
   * Возвращает координату X.
   *
   * @return координата X
   */
  public int getX() {
    return x;
  }

  /**
   * Возвращает координату Y.
   *
   * @return координата Y
   */
  public int getY() {
    return y;
  }

  /**
   * Устанавливает новое значение координаты X.
   *
   * @param x новое значение X
   */
  public void setX(int x) {
    Validation.validateCoordinate(x, "X");
    this.x = x;
  }

  /**
   * Устанавливает новое значение координаты Y.
   *
   * @param y новое значение Y
   */
  public void setY(int y) {
    Validation.validateCoordinate(y, "Y");
    this.y = y;
  }

  /**
   * Демонстрационный метод.
   * Запрашивает координаты трех точек и выводит результаты их сравнения.
   */
  public static void demonstrate() {
    Scanner scanner = new Scanner(System.in);

    ComparingPoints[] points = new ComparingPoints[3];

    for (int i = 0; i < 3; i++) {
      System.out.println("\nТочка " + (i + 1) + ":");
      int x = getValidInt(scanner, "X");
      int y = getValidInt(scanner, "Y");
      points[i] = new ComparingPoints(x, y);
    }

    System.out.println("\nСозданные точки:");
    for (int i = 0; i < points.length; i++) {
      System.out.println((i + 1) + ". " + points[i]);
    }

    System.out.println("\nРезультаты сравнения:");
    System.out.println("Точка 1 равна Точке 2: " + points[0].equals(points[1]));
    System.out.println("Точка 1 равна Точке 3: " + points[0].equals(points[2]));
    System.out.println("Точка 2 равна Точке 3: " + points[1].equals(points[2]));

  }

  /**
   * Вспомогательный метод для получения валидного целого числа с проверкой.
   *
   * @param scanner объект Scanner для ввода
   * @param coordinateName название координаты (X или Y)
   * @return валидное целое число
   */
  private static int getValidInt(Scanner scanner, String coordinateName) {
    while (true) {
      try {
        System.out.print(coordinateName + ": ");
        String input = scanner.next();
        return Validation.validateInt(input, coordinateName);
      } catch (RuntimeException e) {
        System.out.println("Ошибка валидации: " + e.getMessage());
        scanner.nextLine();
      }
    }
  }
}
