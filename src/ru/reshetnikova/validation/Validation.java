// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг операторов сравнения и присваивания
package ru.reshetnikova.validation;

/**
 * Утилитарный класс для валидации различных типов данных.
 * Содержит методы проверки имен, телефонов, координат, чисел и других полей.
 *
 * @author Решетникова
 * @version 1.0
 */
public class Validation {
  /**
   * Проверяет, что имя не является null и не состоит только из пробелов.
   *
   * @param name проверяемое имя
   * @throws EmptyFieldException если имя пустое
   */
  public static void validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new EmptyFieldException("Имя не может быть null или пустой строкой");
    }
  }

  /**
   * Проверяет, что хотя бы одно из полей (имя, фамилия, отчество) заполнено.
   *
   * @param firstName имя
   * @param lastName фамилия
   * @param patronymic отчество
   * @throws EmptyFieldException если все поля пустые
   */
  public static void validateNameParts(String firstName, String lastName, String patronymic) {
    // FIX_ME: добавлен перенос строки
    if ((firstName == null || firstName.trim().isEmpty()) &&
        (lastName == null || lastName.trim().isEmpty()) &&
        (patronymic == null || patronymic.trim().isEmpty())) {
      // FIX_ME: добавлен перенос строки
      throw new EmptyFieldException(
          "Как минимум одно из полей (имя, фамилия, отчество) должно быть заполнено");
    }
  }

  /**
   * Проверяет, что вместимость положительная.
   *
   * @param capacity проверяемая вместимость
   * @throws InvalidNumberException если вместимость <= 0
   */
  public static void validateCapacity(int capacity) {
    if (capacity <= 0) {
      throw new InvalidNumberException("Вместимость должна быть положительной");
    }
  }

  /**
   * Проверяет, что количество патронов неотрицательное.
   *
   * @param bulletCount количество патронов
   * @throws InvalidNumberException если количество отрицательное
   */
  public static void validateBulletCount(int bulletCount) {
    if (bulletCount < 0) {
      throw new InvalidNumberException("Количество патронов не может быть отрицательным");
    }
  }

  /**
   * Проверяет, что не все поля (имя, фамилия, отчество) пустые.
   *
   * @param firstName имя
   * @param lastName фамилия
   * @param patronymic отчество
   * @throws EmptyFieldException если все поля пустые
   */
  public static void validateAllFieldsEmpty(String firstName, String lastName, String patronymic) {
    boolean firstNameEmpty = firstName == null || firstName.trim().isEmpty();
    boolean lastNameEmpty = lastName == null || lastName.trim().isEmpty();
    boolean patronymicEmpty = patronymic == null || patronymic.trim().isEmpty();

    if (firstNameEmpty && lastNameEmpty && patronymicEmpty) {
      // FIX_ME: добавлен перенос строки
      throw new EmptyFieldException(
          "Все поля пустые. Установлены значения по умолчанию: Иванов Иван Иванович");
    }
  }

  /**
   * Проверяет, что не оба поля пустые.
   *
   * @param field1 первое поле
   * @param field2 второе поле
   * @throws EmptyFieldException если оба поля пустые
   */
  public static void validateTwoFieldsEmpty(String field1, String field2) {
    boolean field1Empty = field1 == null || field1.trim().isEmpty();
    boolean field2Empty = field2 == null || field2.trim().isEmpty();

    if (field1Empty && field2Empty) {
      throw new EmptyFieldException("Оба поля пустые. Установлены значения по умолчанию");
    }
  }

  /**
   * Проверяет корректность номера телефона.
   *
   * @param phone проверяемый номер телефона
   * @throws EmptyFieldException если телефон пустой
   * @throws InvalidPhoneException если телефон содержит нецифровые символы
   */
  public static void validatePhone(String phone) {
    if (phone == null || phone.trim().isEmpty()) {
      throw new EmptyFieldException("Телефон не может быть пустым");
    }

    // Самая простая проверка
    if (!phone.matches("[0-9]+")) {
      throw new InvalidPhoneException("Номер телефона должен состоять только из цифр");
    }
  }

  /**
   * Проверяет, что имя контакта не пустое.
   *
   * @param name имя контакта
   * @throws EmptyFieldException если имя пустое
   */
  public static void validateContactName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new EmptyFieldException("Имя контакта не может быть null или пустой строкой");
    }
  }

  /**
   * Проверяет корректность названия координаты.
   *
   * @param coordinate значение координаты (не проверяется)
   * @param coordinateName название координаты
   * @throws EmptyFieldException если название координаты пустое
   */
  public static void validateCoordinate(int coordinate, String coordinateName) {
    if (coordinateName == null || coordinateName.trim().isEmpty()) {
      throw new EmptyFieldException("Название координаты не может быть пустым");
    }
  }

  /**
   * Проверяет корректность названий координат X и Y.
   *
   * @param x координата X
   * @param y координата Y
   */
  public static void validateCoordinates(int x, int y) {
    validateCoordinate(x, "X");
    validateCoordinate(y, "Y");
  }

  /**
   * Преобразует строку в целое число с валидацией.
   *
   * @param input входная строка
   * @param fieldName название поля для сообщения об ошибке
   * @return целое число
   * @throws EmptyFieldException если строка пустая
   * @throws InvalidNumberException если строка не является целым числом
   */
  public static int validateInt(String input, String fieldName) {
    if (input == null || input.trim().isEmpty()) {
      throw new EmptyFieldException(fieldName + " не может быть пустым");
    }
    try {
      return Integer.parseInt(input.trim());
    } catch (NumberFormatException e) {
      throw new InvalidNumberException(fieldName + " должно быть целым числом");
    }
  }
}

