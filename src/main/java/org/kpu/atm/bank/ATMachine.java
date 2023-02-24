package org.kpu.atm.bank;

import java.util.Scanner;
public class ATMachine {
    private Account [] accountArray; // 고객계좌배열 참조변수
    private int nMachineBalance; // ATM 잔고
    private int nMaxAccountNum; // 고객계좌 참조변수 배열크기
    private int nCurrentAccountNum; // 개설된 고객계좌 수
    private String strManagerPassword; // 관리자 비밀번호
    public static final int BASE_ACCOUNT_ID = 100; // 고객 계좌 발급시 시작 번호
    public ATMachine(int size, int balance, String password) { // 생성자
        this.nMaxAccountNum = size;
        this.nMachineBalance = balance;
        this.strManagerPassword = password;
        this.nCurrentAccountNum = 0;
        this.accountArray = new Account[nMaxAccountNum];
    }
    public void createAccount() { // 계좌 개설
        Scanner scan = new Scanner(System.in);
        System.out.println("----------개설----------");
        System.out.printf("이름 입력:");
        String name = scan.next();
        System.out.printf("암호 입력:");
        String pwd = scan.next();
        this.accountArray[nCurrentAccountNum] = new Account(ATMachine.BASE_ACCOUNT_ID + this.nCurrentAccountNum, 0, name, pwd);
        System.out.printf("%s님 %d번 계좌번호가 정상적으로 개설되었습니다. 감사합니다.\n", name , this.accountArray[nCurrentAccountNum].getnID());
        this.nCurrentAccountNum++;


    }
    public void checkMoney() { // 계좌 조회
        Scanner scan = new Scanner(System.in);
        System.out.println("----------조회----------");
        System.out.printf("계좌번호 입력:");
        int id = scan.nextInt();
        System.out.printf("비밀번호 입력:");
        String pwd = scan.next();
        for (int i = 0; i < this.accountArray.length && this.accountArray[i] != null; i++) {
            if (this.accountArray[i].authenticate(id, pwd)) {
                System.out.printf("계좌 잔액 : %d\n\n", this.accountArray[i].getnBalance());
            }
        }

    }
    public void displayMenu() { // 메인 메뉴 표시
        System.out.println("+--------------------+");
        System.out.println("|      KPU bank      |");
        System.out.println("+--------------------+");
        System.out.println("1. 계좌 개설");
        System.out.println("2. 계좌 조회");
        System.out.println("9. 업무 종료");
    }
}