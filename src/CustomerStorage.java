import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String nameCheckRegExp = "^[А-ЯA-Z][а-яa-zА-ЯA-Z\\-]{0,}"; // RegExp для поиска Имени или Фамилии
        String phoneNumberRegExp = "^(\\+7|8)[0-9]{10}"; // RegExp для тел. номера
        String emailCheckRegExp = "\\A[^@]+@([^@\\.]+\\.)+[^@\\.]+\\z"; // RegExp для почты
        String[] components = data.split("\\s+");
        if(components.length != 4)
            throw new IllegalArgumentException("Неправильный формат команды. Правильно: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        if(!components[0].matches(nameCheckRegExp)) // проверяем формат имени
            throw new IllegalArgumentException("Неправильно задан параметр Имя");
        if(!components[1].matches(nameCheckRegExp)) // проверяем формат фамилии
            throw new IllegalArgumentException("Неправильно задан параметр Фамилия");
        if(!components[3].matches(phoneNumberRegExp)) // проверяем номер телефона
            throw new IllegalArgumentException("Неправильно задан номер телефона");
        if(!components[2].matches(emailCheckRegExp)) // проверяем почтовый ящик
            throw new IllegalArgumentException("Неправильно задан e-mail");
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        if(storage.size() == 0)
            throw  new IllegalArgumentException("Список клиентов пуст");
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if(!storage.containsKey(name))
            throw new IllegalArgumentException("Указанный клиент не найден");
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}