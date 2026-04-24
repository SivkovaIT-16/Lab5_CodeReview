// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг операторов присваивания и сравнения
package ru.reshetnikova.weapon;

/**
 * Абстрактный класс, представляющий оружие.
 * Содержит базовую логику работы с патронами.
 *
 * @author Решетникова
 * @version 1.0
 */
abstract class Weapon {
  private int ammo;

  /**
   * Конструктор оружия.
   *
   * @param ammo начальное количество патронов
   * @throws RuntimeException если количество патронов отрицательное
   */
  public Weapon(int ammo) {
    if (ammo < 0) throw new RuntimeException("Количество патронов не может быть отрицательным");
    this.ammo = ammo;
  }

  /**
   * Абстрактный метод выстрела.
   * Реализуется в классах-наследниках.
   */
  abstract void shoot();

  /**
   * Возвращает текущее количество патронов.
   *
   * @return количество патронов
   */
  public int ammo () {
    return ammo;
  }

  /**
   * Проверяет наличие патронов и расходует один при выстреле.
   *
   * @return true если патрон был израсходован, false если патронов нет
   */
  public boolean getAmmo () {
    if (ammo == 0) {
      return false;
    }
    ammo--;
    return true;
  }

  /**
   * Загружает новое количество патронов.
   *
   * @param ammo новое количество патронов
   * @return предыдущее количество патронов
   * @throws RuntimeException если количество патронов отрицательное
   */
  public  int load (int ammo) {
    if (ammo < 0) {
      throw new RuntimeException("Количество патронов не может быть отрицательным");
    }
    int tmp = ammo;
    this.ammo = ammo;
    return tmp;
  }
}
