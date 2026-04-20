// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
package ru.reshetnikova.validation;

public class InvalidNumberException extends RuntimeException {
  public InvalidNumberException(String message) {
    super(message);
  }
}
