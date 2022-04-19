/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J1.L.P0005;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Menu {

    public static void main(String[] args) {
        int choice = 0;
        Bank login = null;
        BankList ac = new BankList();
        User user = new User();
        List<User> userList = new Vector<>();
        List<Bank> bankList = new Vector<>();
        
        //Bank obj = new Bank();
        Scanner sc = new Scanner(System.in);
        do {
            
            System.out.println("1. Create new account");
            System.out.println("2. Login function");
            System.out.println("3. Withdrawal function");
            System.out.println("4. Deposit function");
            System.out.println("5. Transfer money");
            System.out.println("6. Remove account");
            System.out.println("7. Change password");
            System.out.println("8. Show log");
            System.out.println("9.Exit!!");
            System.out.println("Choice:");
            choice = BooleanDate.getInteger();
            switch (choice) {
                case 1:
                    ac.createNewAcc();
                    break;
                case 2:
                    login = ac.Login();
                    if (login != null) {
                        System.out.println("Login Success!");
                        System.out.println("Wellcome ID:"+login.getId()+"\n"+
                        "Account Balance: "+login.getAmount()+" VND.");
                    } else {
                        System.out.println("Login Faild!! Wrong User ID or Password.");
                    }
                    
                    break;
                case 3:
                    if (login != null) {
                        ac.Withdrawal(login);
                    } else {
                        System.out.println("Please Login");
                    }
                    break;
                case 4:
                    if (login != null) {
                        ac.Deposit(login);
                    } else {
                        System.out.println("Please Login");
                    }
                    break;
                case 5:
                    if (login != null) {
                        sc = new Scanner(System.in);
                        System.out.println("Input account ID: ");
                        String ID = sc.nextLine();
                        if (ac.searchId(ID) >= 0) {
                            System.out.println("Account name: " + ac.get(ac.searchId(ID)).getName());
                        } else {
                            System.out.println("Account ID not exist!");
                            break;
                        }
                        Bank a = new Bank(ID,"",0);
                        sc = new Scanner(System.in);
                        System.out.println("Enter money you want transfer: ");
                        long money = sc.nextLong();
                        if (money > login.getAmount()) {
                            System.out.println("Account not enough money!!");
                        } else {
                            try {
                                sc = new Scanner(System.in);
                                System.out.println("Are you sure for transfer? Yes/No");
                                String ans = sc.nextLine();
                                if (ans.toUpperCase().equals("YES")) {
                                    ac.Transfer(a, login, money);
                                    System.out.println("Transfer complete!!!");
                                    System.out.println("Account ID:"+login.getId()+"\n-"+money+" VND\n"+
                        "Account Balance: "+login.getAmount()+" VND.");

                                }
                                if (!ans.matches("yes|no")) {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Only input Yes or No");
                            }

                        }

                    } else {
                        System.out.println("Please Login");
                    }
                    break;
                case 6:
                    if (login != null) {
                        String ans;
                        while (true) {
                            try {
                                sc = new Scanner(System.in);
                                System.out.println("Do you want to remove this account? Yes/No");
                                ans = sc.nextLine();
                                if (!ans.matches("yes|no")) {
                                    throw new Exception();
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Only input Yes or No");
                            }
                        }
                        ac.remove(ans,login);
                        login = null;
                    } else {
                        System.out.println("Please Login");
                    }
                    break;
                case 7:
                    if(login != null){
                        ac.changePassword(user , login);
                    }else{
                        System.out.println("Please login");
                    }
                    break;
                case 8:
                    if(login != null){
                        ac.showLog(login);
                    }else{
                        System.out.println("Please login");
                    }
                case 9:
                    break;
            }
        } while (choice < 9);
    }
}
