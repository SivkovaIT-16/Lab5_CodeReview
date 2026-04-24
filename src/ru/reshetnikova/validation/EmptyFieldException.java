// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.validation;

/**
 * Исключение, выбрасываемое при обнаружении пустого поля.
 * Используется для валидации строковых полей, которые не могут быть пустыми.
 *
 * @author Решетникова
 * @version 1.0
 */
public class EmptyFieldException extends RuntimeException {
  /**
   * Конструктор исключения.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public EmptyFieldException(String message) {
    super(message);
  }
}
