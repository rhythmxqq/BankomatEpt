package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Account account = new Account();
            Deposit deposit = new Deposit(account);
            Scam scam = new Scam(account);
            new Thread(deposit).start();
            new Thread(scam).start();
        }
    }

    class Account extends Thread{
        private int money = 228;
        private int scam = 2300;
        int qq = 2100;
            public synchronized void toDeposit(){
                while(money >= scam){
                    try{
                        wait();
                    }
                    catch (Exception ex){
                        ex.getMessage();
                    }
                }
                money += 100;
                System.out.println("Баланс пополнен на 100." + "\t" + "Баланc: " + money);
                notify();
            }

        public synchronized void toWithdrawl(){
            while(money < scam){
                try {
                    wait();
                }
                catch (Exception ex){
                    ex.getMessage();
                }
            }
           System.out.println("Введите сумму вывода: ");
            Scanner in = new Scanner(System.in);
            qq = in.nextInt();
            money -= qq;
            if(money < 0){
                System.out.println("не на того напали, кентяры, скама.нет");
                System.exit(0);
            }
            System.out.println("С карты списано " + qq + "\t" + "Баланс: " + money);

            notify();
        }
    }

class Scam extends Thread{
    Account account;
    Scam(Account account){
        this.account=account;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            account.toDeposit();
        }
    }
}
    class Deposit extends Thread{

        Account account;
        Deposit(Account account){
            this.account=account;
        }
        public void run(){
            for (int i = 1; i < 50; i++) {
                account.toWithdrawl();
            }
        }
    }