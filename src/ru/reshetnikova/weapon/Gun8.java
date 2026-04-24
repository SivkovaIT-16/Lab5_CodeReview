// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг бинарных операторов и оператора присваивания
// FIX_ME: комментарии исправлены на более информативные и понятные
// FIX_ME: комментарии начинаются с большой буквы
package ru.reshetnikova.weapon;

import ru.reshetnikova.validation.Validation;

import java.util.Scanner;

/**
 * Класс пистолета, наследующий базовое оружие.
 * Реализует стрельбу, перезарядку и разрядку.
 *
 * <p>Особенности:</p>
 * <ul>
 *   <li>Имеет фиксированную максимальную вместимость</li>
 *   <li>Наследует поле ammo от класса Weapon8</li>
 *   <li>Переопределяет метод shoot() для имитации выстрела</li>
 * </ul>
 *
 * @author Решетникова
 * @version 1.0
 */
public class Gun8 extends Weapon8 {
  private final int maxCapacity;

  /**
   * Конструктор пистолета.
   *
   * @param maxCapacity максимальная вместимость магазина
   */
  public Gun8(int maxCapacity) {
    super(0); // Начинаем с 0 патронов
    Validation.validateCapacity(maxCapacity);
    this.maxCapacity = maxCapacity;
  }

  /**
   * Возвращает максимальную вместимость пистолета.
   *
   * @return максимальная вместимость
   */
  public int getMaxCapacity() {
    return maxCapacity;
  }

  /**
   * Выполняет один выстрел.
   * При наличии патронов уменьшает их количество и выводит "Бах!",
   * иначе выводит "Клац!".
   */
  @Override
  public void shoot() {
    if (ammo > 0) {
      ammo--; // Прямой доступ к ammo
      System.out.println("Бах!");
    } else {
      System.out.println("Клац!");
    }
  }

  /**
   * Заряжает указанное количество патронов.
   *
   * @param bullets количество патронов для зарядки
   * @return количество патронов, которые не поместились
   */
  public int reload(int bullets) {
    Validation.validateBulletCount(bullets);
    int spaceAvailable = maxCapacity - ammo; // Прямой доступ к ammo
    int bulletsToAdd = Math.min(bullets, spaceAvailable);

    ammo += bulletsToAdd; // Прямой доступ к ammo

    return bullets - bulletsToAdd;
  }

  /**
   * Полностью разряжает пистолет.
   *
   * @return количество извлеченных патронов
   */
  public int unload() {
    int unloadedBullets = ammo; // Прямой доступ к ammo
    ammo = 0; // Прямой доступ к ammo
    return unloadedBullets;
  }

  /**
   * Возвращает текущее количество патронов.
   *
   * @return текущее количество патронов
   */
  public int getCurrentBullets() {
    return ammo; // Прямой доступ к ammo
  }

  /**
   * Проверяет, заряжен ли пистолет.
   *
   * @return true если есть хотя бы один патрон
   */
  public boolean isLoaded() {
    return ammo > 0; // Прямой доступ к ammo
  }

  /**
   * Возвращает строковое представление пистолета.
   *
   * @return строка с параметрами пистолета
   */
  @Override
  public String toString() {
    // FIX_ME: добавлен перенос строки
    return "Пистолет8 (вместимость=" + maxCapacity + ", патронов=" + ammo +
        ", заряжен=" + isLoaded() + ")";
  }

  /**
   * Демонстрационный метод.
   * Запрашивает вместимость пистолета и позволяет взаимодействовать с ним
   * через консольное меню (зарядка, выстрелы, разрядка).
   */
  public static void demonstrate() {
    Scanner scanner = new Scanner(System.in);

    // Создаем пистолет с ручным вводом вместимости
    System.out.print("Введите максимальную вместимость пистолета: ");
    try {
      String input = scanner.nextLine();
      int capacity = Validation.validateInt(input, "Вместимость пистолета");
      Gun8 gun = new Gun8(capacity);
      System.out.println("Создан пистолет: " + gun);

      while (true) {
        System.out.println("\nУПРАВЛЕНИЕ ПИСТОЛЕТОМ");
        System.out.println("1. Зарядить патроны");
        System.out.println("2. Выстрелить");
        System.out.println("3. Разрядить");
        System.out.println("4. Показать состояние");
        System.out.println("0. Назад в меню выбора задач");
        System.out.print("Выберите действие: ");

        String choice = scanner.nextLine();

        switch (choice) {
          case "1":
            System.out.print("Введите количество патронов для зарядки: ");
            try {
              input = scanner.nextLine();
              int bullets = Validation.validateInt(input, "Количество патронов");
              int remaining = gun.reload(bullets);
              System.out.println("Успешно заряжено: " + (bullets - remaining) + " патронов");
              if (remaining > 0) {
                System.out.println("Возвращено лишних: " + remaining + " патронов");
              }
              System.out.println("Текущее состояние: " + gun);
            } catch (RuntimeException e) {
              System.out.println("Ошибка: " + e.getMessage());
            }
            break;

          case "2":
            System.out.print("Сколько раз выстрелить? ");
            try {
              input = scanner.nextLine();
              int shots = Validation.validateInt(input, "Количество выстрелов");
              System.out.println("Стреляем " + shots + " раз:");
              for (int i = 0; i < shots; i++) {
                gun.shoot();
              }
              System.out.println("Текущее состояние: " + gun);
            } catch (RuntimeException e) {
              System.out.println("Ошибка: " + e.getMessage());
            }
            break;

          case "3":
            int unloaded = gun.unload();
            System.out.println("Пистолет разряжен! Возвращено патронов: " + unloaded);
            System.out.println("Текущее состояние: " + gun);
            break;

          case "4":
            System.out.println(gun);
            break;

          case "0":
            return;

          default:
            System.out.println("Неверный выбор!");
        }
      }
    } catch (RuntimeException e) {
      System.out.println("Ошибка при создании пистолета: " + e.getMessage());
    }
  }
}
