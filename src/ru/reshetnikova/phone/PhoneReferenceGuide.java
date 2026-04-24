// FIX_ME: фигурные скобки перенесены на строку с объявлением
// FIX_ME: удалены пробелы между названием метода и открывающейся скобкой для параметров
// FIX_ME: изменены отступы на 2 пробела вместо 4
// FIX_ME: добавлены пробелы вокруг бинарных операторов и оператора присваивания
// FIX_ME: комментарии начинаются с большой буквы
package ru.reshetnikova.phone;

import ru.reshetnikova.validation.InvalidPhoneException;
import ru.reshetnikova.validation.Validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Класс телефонного справочника, обеспечивающий двустороннее отображение
 * между именами контактов и номерами телефонов.
 *
 * <p>Особенности реализации:</p>
 * <ul>
 *   <li>Каждому имени соответствует один телефон</li>
 *   <li>Телефоны уникальны и не могут принадлежать разным контактам</li>
 *   <li>Поддерживается поиск как по имени, так и по телефону</li>
 *   <li>Валидация входных данных выполняется через класс Validation</li>
 * </ul>
 *
 * @author Решетникова
 * @version 1.0
 */
public class PhoneReferenceGuide {
  // FIX_ME: имена полей переименованы, должны начинаться с меленькой буквы
  //private Map<String, String> Contacts;
  //private Map<String, String> PhoneToName;
  private Map<String, String> contacts; // Имя - телефон
  private Map<String, String> phoneToName; // Телефон - имя (для быстрой проверки уникальности)

  /**
   * Конструктор по умолчанию.
   * Инициализирует пустые HashMap для хранения контактов.
   */
  public PhoneReferenceGuide() {
    this.contacts = new HashMap<>();
    this.phoneToName = new HashMap<>();
  }

  /**
   * Добавляет новый контакт или обновляет телефон существующего.
   *
   * @param phone номер телефона
   * @param name имя контакта
   * @return предыдущий номер телефона, если имя уже существовало, иначе null
   * @throws InvalidPhoneException если телефон уже принадлежит другому контакту
   */
  public String addContact(String phone, String name) {
    Validation.validatePhone(phone);
    Validation.validateContactName(name);

    String cleanPhone = phone.trim();
    String cleanName = name.trim();

    // Проверяем, не используется ли телефон у другого человека
    if (phoneToName.containsKey(cleanPhone) && !phoneToName.get(cleanPhone).equals(cleanName)) {
      String existingName = phoneToName.get(cleanPhone);
      // FIX_ME: добавлен перенос строки
      throw new InvalidPhoneException("Телефон " + cleanPhone +
          " уже используется у контакта: " + existingName);
    }

    String oldPhone = contacts.put(cleanName, cleanPhone);

    // Обновляем обратное отображение
    if (oldPhone != null) {
      phoneToName.remove(oldPhone); // Удаляем старый телефон
    }
    phoneToName.put(cleanPhone, cleanName); // Добавляем новый телефон

    return oldPhone; // Возвращает старый телефон или null если не было
  }

  /**
   * Удаляет контакт по имени.
   *
   * @param name имя контакта для удаления
   * @return номер телефона удаленного контакта, или null если контакт не найден
   */
  public String removeContact(String name) {
    Validation.validateContactName(name);
    String cleanName = name.trim();

    String phone = contacts.remove(cleanName);
    if (phone != null) {
      phoneToName.remove(phone); // Удаляем из обратного отображения
    }
    return phone; // Возвращает телефон удаленного контакта или null
  }

  /**
   * Возвращает номер телефона по имени контакта.
   *
   * @param name имя контакта
   * @return номер телефона или null, если контакт не найден
   */
  public String getPhone(String name) {
    Validation.validateContactName(name);
    return contacts.get(name.trim());
  }

  /**
   * Возвращает имя контакта по номеру телефона.
   *
   * @param phone номер телефона
   * @return имя контакта или null, если телефон не найден
   */
  public String getNameByPhone(String phone) {
    Validation.validatePhone(phone);
    return phoneToName.get(phone.trim());
  }

  /**
   * Проверяет наличие телефона в справочнике.
   *
   * @param phone номер телефона
   * @return true если телефон существует, иначе false
   */
  public boolean containsPhone(String phone) {
    Validation.validatePhone(phone);
    return phoneToName.containsKey(phone.trim());
  }

  /**
   * Проверяет наличие имени в справочнике.
   *
   * @param name имя контакта
   * @return true если имя существует, иначе false
   */
  public boolean containsName(String name) {
    Validation.validateContactName(name);
    return contacts.containsKey(name.trim());
  }

  /**
   * Возвращает общее количество контактов в справочнике.
   *
   * @return количество контактов
   */
  public int getContactCount() {
    return contacts.size();
  }

  /**
   * Возвращает все пары (имя, телефон) в виде двумерного массива.
   *
   * @return массив размером [n][2], где n - количество контактов
   */
  public String[][] getAllPairs() {
    String[][] pairs = new String[contacts.size()][2];
    int i = 0;
    for (Map.Entry<String, String> entry : contacts.entrySet()) {
      pairs[i][0] = entry.getKey(); // Имя
      pairs[i][1] = entry.getValue(); // Телефон
      i++;
    }
    return pairs;
  }

  /**
   * Возвращает массив всех телефонных номеров.
   *
   * @return массив телефонов
   */
  public String[] getAllPhones() {
    return contacts.values().toArray(new String[0]);
  }

  /**
   * Возвращает массив всех имен контактов.
   *
   * @return массив имен
   */
  public String[] getAllNames() {
    return contacts.keySet().toArray(new String[0]);
  }

  /**
   * Возвращает имена контактов, начинающиеся с указанного префикса.
   *
   * @param prefix префикс для поиска (регистронезависимый)
   * @return массив имен, соответствующих префиксу
   */
  public String[] getNamesByPrefix(String prefix) {
    if (prefix == null) {
      prefix = "";
    }
    final String finalPrefix = prefix.toLowerCase().trim();

    ArrayList<String> result = new ArrayList<>();
    for (String name : contacts.keySet()) {
      if (name.toLowerCase().startsWith(finalPrefix)) {
        result.add(name);
      }
    }
    return result.toArray(new String[0]);
  }

  /**
   * Возвращает строковое представление справочника.
   *
   * @return отформатированный список контактов или сообщение о пустоте
   */
  @Override
  public String toString() {
    if (contacts.isEmpty()) {
      return "Телефонный справочник пуст";
    }

    StringBuilder sb = new StringBuilder("Телефонный справочник:\n");
    for (Map.Entry<String, String> entry : contacts.entrySet()) {
      sb.append(entry.getValue()).append(" - ").append(entry.getKey()).append("\n");
    }
    return sb.toString();
  }

  /**
   * Демонстрационный метод. Запускает интерактивное консольное меню
   * для тестирования функциональности телефонного справочника.
   */
  public static void demonstrate() {
    Scanner scanner = new Scanner(System.in);
    PhoneReferenceGuide phoneBook = new PhoneReferenceGuide();

    while (true) {
      System.out.println("\nТЕЛЕФОННЫЙ СПРАВОЧНИК");
      System.out.println("1. Добавить контакт");
      System.out.println("2. Найти телефон по имени");
      System.out.println("3. Найти имя по телефону");
      System.out.println("4. Удалить контакт");
      System.out.println("5. Показать все контакты");
      System.out.println("6. Проверить наличие телефона");
      System.out.println("7. Проверить наличие имени");
      System.out.println("8. Получить статистику");
      System.out.println("9. Поиск по началу имени");
      System.out.println("0. Назад в меню выбора задач");
      System.out.print("Выберите действие: ");

      String choice = scanner.nextLine();

      switch (choice) {
        case "1":
          System.out.print("Введите имя: ");
          String name = scanner.nextLine();
          System.out.print("Введите телефон: ");
          String phone = scanner.nextLine();
          try {
            String oldPhone = phoneBook.addContact(phone, name);
            if (oldPhone != null) {
              System.out.println("Контакт обновлен! Старый телефон: " + oldPhone);
            } else {
              System.out.println("Контакт добавлен!");
            }
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "2":
          System.out.print("Введите имя для поиска: ");
          name = scanner.nextLine();
          try {
            phone = phoneBook.getPhone(name);
            if (phone != null) {
              System.out.println("Телефон: " + phone);
            } else {
              System.out.println("Контакт не найден");
            }
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "3":
          System.out.print("Введите телефон для поиска: ");
          phone = scanner.nextLine();
          try {
            name = phoneBook.getNameByPhone(phone);
            if (name != null) {
              System.out.println("Имя: " + name);
            } else {
              System.out.println("Контакт не найден");
            }
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "4":
          System.out.print("Введите имя для удаления: ");
          name = scanner.nextLine();
          try {
            phone = phoneBook.removeContact(name);
            if (phone != null) {
              System.out.println("Контакт удален! Телефон: " + phone);
            } else {
              System.out.println("Контакт не найден");
            }
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "5":
          System.out.println(phoneBook);
          break;

        case "6":
          System.out.print("Введите телефон для проверки: ");
          phone = scanner.nextLine();
          try {
            boolean exists = phoneBook.containsPhone(phone);
            System.out.println("Телефон " + (exists ? "найден" : "не найден") + " в справочнике");
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "7":
          System.out.print("Введите имя для проверки: ");
          name = scanner.nextLine();
          try {
            boolean exists = phoneBook.containsName(name);
            System.out.println("Имя " + (exists ? "найдено" : "не найдено") + " в справочнике");
          } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
          }
          break;

        case "8":
          System.out.println("Количество контактов: " + phoneBook.getContactCount());
          System.out.println("Все имена: " + String.join(", ", phoneBook.getAllNames()));
          System.out.println("Все телефоны: " + String.join(", ", phoneBook.getAllPhones()));
          break;

        case "9":
          System.out.print("Введите начало имени: ");
          String prefix = scanner.nextLine();
          String[] names = phoneBook.getNamesByPrefix(prefix);
          if (names.length > 0) {
            System.out.println("Найдены контакты:");
            for (String foundName : names) {
              System.out.println("- " + foundName + ": " + phoneBook.getPhone(foundName));
            }
          } else {
            System.out.println("Контакты не найдены");
          }
          break;

        case "0":
          return;

        default:
          System.out.println("Неверный выбор!");
      }
    }
  }
}
