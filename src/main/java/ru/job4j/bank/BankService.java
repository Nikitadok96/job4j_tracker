package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу модели банковской системы
 * @author Nikita Vozhegov
 * @version 1.0
 */
public class BankService {
    /**
     * Поле содержит всех пользователей системы с привязанными к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя в систему
     * @param user класса User будет добавлен в Map users
     * При добавлении происходит проверка, что пользователя ещё нет в системе.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Данный метод позволяет удалить пользователя из системы
     * @param passport является уникальным идентификатором, на основании которого происходит удачение
     * @return true, если пользователь был найден и удалён. Иначе вернёт false
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Данный метод добавляет новый счет к пользователю
     * @param passport является уникальным идентификатором, на основании которого происходит
     * поиск пользователя с помощью метода findByPassport(passport), после чего происходит
     * поиск списка счетов данного пользователя
     * @param account используется для проверки, что такого счета у пользователя еще нет.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accountList = getAccounts(user);
            if (!accountList.contains(account)) {
                accountList.add(account);
            }
        }
    }

    /**
     * Этот метод ищет пользователя по номеру паспорта.
     * @param passport используется для поиска пользователя
     * @return объект найденного пользователя класса User
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод ищет счет пользователя по реквизитам:
     * @param passport используется для поиска пользователя по пасспорту
     * @param requisite используется для поиска счёта у пользователя
     * @return объёкт найденного счёта класса Account
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accountList = getAccounts(user);
            for (Account account : accountList) {
                if (requisite.equals(account.getRequisite())) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Метод предназначен для перечисления денег с одного счёта на другой счёт.
     * @param srcPassport используется для поиска пользователя отправителя
     * @param srcRequisite используется для поиска счёта пользователя отправителя
     * @param destPassport используется для поиска пользователя получателя
     * @param destRequisite используется для поиска счёта пользователя получателя
     * @param amount количество перечисляемых средств
     * @return true при успешном перечислении средств, иначе возвращает false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && srcAccount.getBalance() >= amount && destAccount != null) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод возвращает список всех счетов пользователя
     * @param user используется для поиска пользователя
     * @return список всех счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
