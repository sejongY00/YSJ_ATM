package org.kpu.atm.bank;

import org.kpu.atm.main.Statistics;

import java.util.List;
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
        System.out.printf("이름 입력 : ");
        String name = scan.next();
        System.out.printf("암호 입력 : ");
        String pwd = scan.next();
        this.accountArray[nCurrentAccountNum] = new Account(ATMachine.BASE_ACCOUNT_ID + this.nCurrentAccountNum, 0, name, pwd);
        System.out.printf("%s님 %d번 계좌번호가 정상적으로 개설되었습니다. 감사합니다.\n", name , this.accountArray[nCurrentAccountNum].getnID());
        System.out.println();
        this.nCurrentAccountNum++;


    }
    public void checkMoney() { // 계좌 조회
        Scanner scan = new Scanner(System.in);
        System.out.println("----------조회----------");
        System.out.printf("계좌번호 입력: ");
        int id = scan.nextInt();
        System.out.printf("비밀번호 입력: ");
        String pwd = scan.next();
        for (int i = 0; i < this.accountArray.length; i++) {
            if (this.accountArray[0] == null) {
                System.out.println("회원 정보가 없습니다.");
            }
            if (this.accountArray[i] == null) {
                break;
            }
            if (this.accountArray[i].authenticate(id, pwd)) {
                System.out.printf("계좌 잔액 : %d\n", this.accountArray[i].getnBalance());
            }
        }
        System.out.println();
    }

    public void depositMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------입금----------");
        System.out.printf("계좌번호 입력: ");
        int id = scan.nextInt();
        System.out.printf("비밀번호 입력: ");
        String pwd = scan.next();
        System.out.printf("입금 액 입력: ");
        int money = scan.nextInt();
        for (int i = 0; i < this.accountArray.length; i++) {
            if (this.accountArray[i] == null) {
                break;
            }
            if (this.accountArray[i].authenticate(id, pwd)) {
                System.out.printf("입금 후 잔액 : %d\n", this.accountArray[i].deposit(money));
            }
        }
        System.out.println();
        nMachineBalance+=money;
    }

    public void widrawMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------출금----------");
        System.out.printf("계좌번호 입력: ");
        int id = scan.nextInt();
        System.out.printf("비밀번호 입력: ");
        String pwd = scan.next();
        System.out.printf("출금 액 입력: ");
        int money = scan.nextInt();
        for (int i = 0; i < this.accountArray.length; i++) {
            if (this.accountArray[i] == null) {
                break;
            }
            if (this.accountArray[i].authenticate(id, pwd)) {
                System.out.printf("출금 후 잔액 : %d\n", this.accountArray[i].widraw(money));
            }
        }
        System.out.println();
        nMachineBalance-=money;
    }

    public void managerMode() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---------고객관리---------");
        System.out.printf("관리자 비밀번호 입력: ");
        String adminPwd = scan.next();
        if (adminPwd.equals(this.strManagerPassword)) {
            System.out.printf(" ATM 현금 잔고: %d\n", nMachineBalance);
            System.out.printf("고객 잔고 총액: %d(%d명)\n", Statistics.getUserTotal(this.accountArray), nCurrentAccountNum);
            System.out.printf("고객 잔고 평균: %d\n", Statistics.getUserAvg(this.accountArray));
            System.out.printf("고객 잔고 최고: %d\n", Statistics.getUserMax(this.accountArray));
            System.out.println("고객 계좌 현황(고객 잔고 내림 차순 정렬)");
            List<Account> accounts = Statistics.getUserSort(this.accountArray);
            for(Account a : accounts) {
                System.out.printf("%s \t %d \t %d원 \t \n", a.getAccountName(), a.getnID(), a.getnBalance());
            }
        }
        System.out.println();
    }


    public void displayMenu() { // 메인 메뉴 표시
        System.out.println("+--------------------+");
        System.out.println("|      KPU bank      |");
        System.out.println("+--------------------+");
        System.out.println("1. 계좌 개설");
        System.out.println("2. 계좌 조회");
        System.out.println("3. 계좌 입금");
        System.out.println("4. 계좌 출금");
        System.out.println("5. 고객 관리");
        System.out.println("9. 업무 종료");
    }
}