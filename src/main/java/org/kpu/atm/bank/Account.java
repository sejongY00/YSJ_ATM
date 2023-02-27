package org.kpu.atm.bank;


public class Account {
    private int nID; // 계좌 번호
    private int nBalance; // 계좌 잔고
    private String strAccountName; // 고객 명
    private String strPassword; // 계좌 비밀번호
    public Account(int id, int money, String name, String password) { // 생성자
        this.nID = id;
        this.nBalance = money;
        this.strAccountName = name;
        this.strPassword = password;
    }
    boolean authenticate(int id, String passwd) { // 계정 확인
        if (this.nID == id && this.strPassword.equals(passwd)) {
            return true;
        }
        return false;
    }

    public int deposit(int money) {
        return this.nBalance += money;
    }

    public int widraw(int money) {
        return this.nBalance -= money;
    }

    public String getAccountName() {
        return strAccountName;
    }

    public int getnID() {
        return nID;
    }
    public int getnBalance() {
        return nBalance;
    }

}