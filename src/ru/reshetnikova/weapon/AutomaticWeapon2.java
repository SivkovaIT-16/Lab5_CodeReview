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
 * Класс автоматического оружия, наследующий базовое оружие.
 * Реализует стрельбу очередью, перезарядку и разрядку.
 *
 * <p>Особенности:</p>
 * <ul>
 *   <li>Имеет фиксированную максимальную вместимость</li>
 *   <li>Имеет скорострельность (количество выстрелов за очередь)</li>
 *   <li>При стрельбе выпускает очередь с учетом оставшихся патронов</li>
 * </ul>
 *
 * @author Решетникова
 * @version 1.0
 */
public class AutomaticWeapon2 extends Weapon {
  private final int maxCapacity;
  private final int fireRate;

  /**
   * Конструктор автоматического оружия.
   *
   * @param maxCapacity максимальная вместимость магазина
   * @param fireRate скорострельность (выстрелов в секунду/очередь)
   */
  public AutomaticWeapon2(int maxCapacity, int fireRate) {
    super(0); // начинаем с 0 патронов
    this.maxCapacity = maxCapacity;
    this.fireRate = fireRate;
  }

  /**
   * Возвращает максимальную вместимость.
   *
   * @return максимальная вместимость
   */
  public int getMaxCapacity() {
    return maxCapacity;
  }

  /**
   * Возвращает скорострельность.
   *
   * @return скорострельность
   */
  public int getFireRate() {
    return fireRate;
  }

  /**
   * Выполняет стрельбу очередью.
   * Количество выстрелов в очереди равно скорострельности,
   * но не может превышать количество оставшихся патронов.
   */
  @Override
  public void shoot() {
    int actualShots = Math.min(ammo(), fireRate);
    int emptyShots = fireRate - actualShots;

    System.out.println("Автоматическая очередь (" + fireRate + " выстрелов):");

    // Реальные выстрелы
    for (int i = 0; i < actualShots; i++) {
      if (getAmmo()) {
        System.out.println("Бах!");
      } else {
        break;
      }
    }

    // Холостые выстрелы (если скорострельность больше чем патронов)
    for (int i = 0; i < emptyShots; i++) {
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
    int spaceAvailable = maxCapacity - ammo();
    int bulletsToAdd = Math.min(bullets, spaceAvailable);

    // ВАЖНО: не используем load() для добавления, а вычисляем новое значение
    int newAmmoCount = ammo() + bulletsToAdd;
    load(newAmmoCount); // Заменяет количество патронов

    return bullets - bulletsToAdd;
  }

  /**
   * Полностью разряжает оружие.
   *
   * @return количество извлеченных патронов
   */
  public int unload() {
    int unloadedBullets = ammo();
    load(0); // Устанавливаем количество патронов в 0
    return unloadedBullets;
  }

  /**
   * Возвращает строковое представление автомата.
   *
   * @return строка с параметрами оружия
   */
  @Override
  public String toString() {
    return "Автомат (вместимость=" + maxCapacity +
        ", патронов=" + ammo() +
        ", скорострельность=" + fireRate + " в/с)";
  }

  /**
   * Демонстрационный метод.
   * Запрашивает параметры автомата, создает его и демонстрирует зарядку и стрельбу.
   */
  public static void demonstrate() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите вместимость автомата: ");
    String capacityInput = scanner.nextLine();
    int capacity = Integer.parseInt(capacityInput);

    System.out.print("Введите скорострельность (выстрелов в секунду): ");
    String fireRateInput = scanner.nextLine();
    int fireRate = Integer.parseInt(fireRateInput);

    AutomaticWeapon2 weapon = new AutomaticWeapon2(capacity, fireRate);
    System.out.println("Создан автомат: " + weapon);

    // Простая демонстрация
    System.out.print("Сколько патронов зарядить? ");
    String bulletsInput = scanner.nextLine();
    int bullets = Integer.parseInt(bulletsInput);
    weapon.reload(bullets);

    System.out.println("После зарядки: " + weapon);
    System.out.println("Стреляем:");
    weapon.shoot();
    System.out.println("После стрельбы: " + weapon);
  }
}
