package org.kpu.atm.main;
import org.kpu.atm.bank.Account;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Statistics {
    public static int getUserTotal(Account[] accountArray) {
        int sum = 0;
        for (Account account : accountArray) {
            if (account == null) {
                break;
            }
            sum += account.getnBalance();
        }
        return sum;
    }
    public static int getUserAvg(Account[] accountArray) {
        int sum = 0;
        int avg = 0;
        for (int i = 0; i < accountArray.length; i++) {
            if (accountArray[i] == null) {
                break;
            }
            sum += accountArray[i].getnBalance();
            avg = sum / (i+1);
        }
        return avg;
    }
    public static int getUserMax(Account[] accountArray) {
        int max = 0;
        for (Account account : accountArray) {
            if (account == null) {
                break;
            }
            max = Math.max(max, account.getnBalance());
        }
        return max;
    }
    public static List<Account> getUserSort(Account[] accountArray) {
        List<Account> accounts = Arrays.stream(accountArray).filter(Objects::nonNull).sorted(Comparator.comparing(Account::getnBalance).reversed()).collect(Collectors.toList());

        return accounts;
    }
}
