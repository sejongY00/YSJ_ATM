package org.kpu.atm.main;

import org.kpu.atm.bank.ATMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmMain {
    public static void main(String[] args) {
// 초기 설정 - 개설가능한 최대계좌수(1000계좌),
// ATM 잔금(50만원), 관리자 암호
        ATMachine atm = new ATMachine(1000, 500000, "admin");
        Scanner scan = new Scanner(System.in);
        while (true) {
            atm.displayMenu();
            System.out.printf(" 메뉴를 선택하세요 >> ");
            try {
                int select = scan.nextInt();
                switch (select) {
                    case 1: // 계좌 개설
                        atm.createAccount();
                        break;

                    case 2:
                        atm.checkMoney();
                        break;
                }
            } catch(InputMismatchException e) {
                    System.out.println(" 정확하게 입력해주세요.");
                    continue;
            }
        }
    }
}
