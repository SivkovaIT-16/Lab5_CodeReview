// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.validation;

/**
 * Исключение, выбрасываемое при обнаружении некорректных координат.
 * Используется для валидации координат точек в геометрических задачах.
 *
 * @author Решетникова
 * @version 1.0
 */
public class InvalidCoordinateException extends RuntimeException {
  /**
   * Конструктор исключения.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public InvalidCoordinateException(String message) {
    super(message);
  }
}
