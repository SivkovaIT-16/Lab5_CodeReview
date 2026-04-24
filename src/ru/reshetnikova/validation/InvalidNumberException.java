// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.validation;

/**
 * Исключение, выбрасываемое при обнаружении некорректного числа.
 * Используется для валидации числовых значений в различных задачах.
 *
 * @author Решетникова
 * @version 1.0
 */
public class InvalidNumberException extends RuntimeException {
  /**
   * Конструктор исключения.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public InvalidNumberException(String message) {
    super(message);
  }
}
