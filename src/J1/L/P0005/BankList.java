/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J1.L.P0005;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public  class BankList extends ArrayList<Bank> {

    ArrayList<User> userList;
    ArrayList<Log> logList;
    String id;
    String name;
    String pwd;
    long amount;
    User a;
    Bank b;

    public  BankList() {
        userList = new ArrayList();
        logList = new ArrayList();
        loadAccountFromFileBank("Bank.dat");
        loadAccountFromFileUser("User.dat");
        loadAccountFromFileLog("Log.dat");
        
    }
    public void loadAccountFromFileBank (String fName) {
        try {
            File f = new File (fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Bank ac;
            while ((ac=(Bank)(fo.readObject())) != null) {
                this.add(ac);
            }
            fo.close(); fi.close();
        } catch (Exception e) {
            
        }
    }
    
    public void loadAccountFromFileUser (String fName) {
        try {
            File f = new File (fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            User ac;
            while ((ac=(User)(fo.readObject())) != null) {
                String pass = ac.getPwd();
                userList.add(ac);
            }
            fo.close(); fi.close();
        } catch (Exception e) {
            
        }
    }
    public void loadAccountFromFileLog (String fName) {
        try {
            File f = new File (fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Log ac;
            while ((ac=(Log)(fo.readObject())) != null) {
                logList.add(ac);
            }
            fo.close(); fi.close();
        } catch (Exception e) {
            
        }
    }
    public void saveToFileUser (String fName) {
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (User thi : userList) {
                fo.writeObject(thi);
            }
            fo.flush();
            fo.close(); f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void saveToFileLog (String fName) {
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Log thi : logList) {
                fo.writeObject(thi);
            }
            fo.flush();
            fo.close(); f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToFileBank (String fName) {
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Bank thi : this) {
                fo.writeObject(thi);
            } 
            fo.flush();
            fo.close(); f.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Bank searchAccountID(String id) {
        for (Bank a : this) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public User searchUserId(String id) {
        for (User a : userList) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }
    public Log searchLogId(String id) {
        for (Log a : logList) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public int searchId(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int searchName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void createNewAcc() {
        int pos;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Enter  UserID(XXXXX):");
            id = sc.nextLine();
            while (!BooleanDate.isValidId(id)) {
                System.out.println("Enter incorrectly, please re-enter");
                id = sc.nextLine();
            }
            pos = searchId(id);
            if (pos >= 0) {
                System.out.println("ID is duplicated");
            }

        } while (pos >= 0);
        boolean nhaptiep = true;
        do {
            try {
                sc = new Scanner(System.in);
                do {
                    System.out.println("Enter account name: ");
                    name = BooleanDate.inputString("^[a-zA-Z1-9 ]{1,30}$");
                    pos = searchName(name);
                    if (pos >= 0) {
                        System.out.println("Account already exists!");

                    }
                } while (pos >= 0);

                nhaptiep = false;
            } catch (Exception e) {
                System.out.println("Please re-enter information");
                nhaptiep = true;
            }
        } while (nhaptiep);
        do {
            try {
                String repwd;
                boolean r = true;
                sc = new Scanner(System.in);
                System.out.println("Enter password: ");
                pwd = BooleanDate.inputString("^[a-zA-Z~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/? ]{6,30}$");
                do {
                    System.out.println("Re-Enter password: ");
                    repwd = BooleanDate.inputString("^[a-zA-Z~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/? ]{6,30}$");
                    if (repwd.equals(pwd)) {
                        r = false;
                    } else {
                        System.out.println("The two passwords must be the same!!");
                    }

                } while (r);

                if (pwd == null) {
                    throw new Exception();
                }
                nhaptiep = false;
            } catch (Exception e) {
                System.out.println("Please re-enter password:password must contain at least 6 characters, including uppercase letters, lower case\n"
                        + "letters, numeric characters and 1 special character");
                nhaptiep = true;
            }
        } while (nhaptiep);
        String codeSrt = CaesarCodeing.cearDecode123(pwd);
        this.add(new Bank(id, name, 0));
        userList.add(new User(id, codeSrt));
        saveToFileBank("Bank.dat");
        saveToFileUser("User.dat");
        System.out.println("Create new acc success!!!");
    }

    public Bank Login() {
        String id, pass;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Input Account ID: ");
            id = sc.nextLine();
            System.out.println("Input Password: ");
            sc = new Scanner(System.in);
            pass = sc.nextLine();
            int index = searchId(id);
            Bank dto = this.get(index);
         String  codeSrt = CaesarCodeing.cearDecode123(pass);
            if (index >= 0) {
                if (codeSrt.equals(userList.get(index).getPwd())) {
                    return dto;
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
        }

        return null;
    }
    public void changePassword(User a , Bank b){
        String changePwd = null , pwd , repwd , codeStr , codeSrt;
        boolean r = true , con = true ,conti = true;
        do{
                  
            Scanner sc =new Scanner(System.in);
            System.out.println("Input old password: ");
            pwd = sc.nextLine();
            codeStr = CaesarCodeing.cearDecode123(pwd);
            int index = searchId(b.getId());
            try {  
            if(codeStr.equals(userList.get(index).getPwd())){
               do{ try {
                       System.out.println("Input new password: ");
                       changePwd = BooleanDate.inputString("^[a-zA-Z~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/? ]{6,30}$");
                       conti = false;
                   } catch (Exception e) {
                       System.out.println("Please re-enter password:password must contain at least 6 characters, including uppercase letters, lower case\n"
                        + "letters, numeric characters and 1 special character");
                       conti = true;
                   }
                   }
                while(conti);
                do {
                    System.out.println("Re-Enter password: ");
                    repwd = BooleanDate.inputString("^[a-zA-Z~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/? ]{6,30}$");
                    if (repwd.equals(changePwd)) {
                        codeSrt = CaesarCodeing.cearDecode123(repwd);
                        userList.get(index).setPwd(codeSrt);
                        System.out.println("Change password complete!!!");
                        logList.add(new Log(b.getId(), "Change password"));
                        saveToFileLog("Log.dat");
                        saveToFileUser("User.dat");
                       con = false;
                       r = false;
                    } else {
                        System.out.println("The two passwords must be the same!!");
                    }
                } while (r);
            
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Wrong password please input again!!");
        }
        }while(con);
    }

    public void Withdrawal(Bank a) {
        long result, withdrawal;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input money withdrawal: ");
            withdrawal = sc.nextLong();
            if (withdrawal > a.getAmount()) {
                System.out.println("Your account not enough money!!!");
            } else {
                try {
                    sc = new Scanner(System.in);
                    System.out.println("Are you sure for deposit? Yes/No");
                    String ans = sc.nextLine();
                    if (ans.toUpperCase().equals("YES")) {
                        result = a.getAmount() - withdrawal;
                        a.setAmount(result);
                        logList.add(new Log(a.getId(), "Withdrawal:"+"-"+withdrawal+"VND-Balance:"+result+"VND."));
                    saveToFileLog("Log.dat");
                        saveToFileBank("Bank.dat");
                        System.out.println("Withfrawal success!!!");
                        System.out.println("Account ID:" + a.getId() + "\n-" + withdrawal + " VND\n"
                                + "Account Balance: " + a.getAmount() + " VND.");

                    }
                    if (!ans.matches("yes|no")) {
                        throw new Exception();
                    }

                } catch (Exception e) {
                    System.out.println("Only input Yes or No");
                }
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                System.out.println("Input digit number!!!");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

    public void Deposit(Bank a) {
        long result = 0;
        long deposit;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input money deposit: ");
            deposit = sc.nextLong();
            try {
                sc = new Scanner(System.in);
                System.out.println("Are you sure for deposit? Yes/No");
                String ans = sc.nextLine();
                if (ans.toUpperCase().equals("YES")) {
                    result = deposit + a.getAmount();
                    a.setAmount(result);
                    logList.add(new Log(a.getId(), "Deposit:"+"+"+deposit+"VND"+"-Balance:"+result+"VND."));
                    saveToFileLog("Log.dat");
                    saveToFileBank("Bank.dat");                   
                    System.out.println("Deposit complete!!!");
                    System.out.println("Account ID:" + a.getId() + "\n+" + deposit + " VND\n"
                            + "Account Balance: " + a.getAmount() + " VND.");

                }
                if (!ans.matches("yes|no")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Only input Yes or No");
            }

        } catch (Exception e) {
            if (e.getMessage() == null) {
                System.out.println("Input digit number!!!");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }
public void showLog(Bank a) {
        Date current = new Date();
        boolean hasLog = false;
        if (!logList.isEmpty()) {
            System.out.println(" Log Activity in current month.");
            for (Log logDTO : logList) {
                if (a.getId().equals(logDTO.getId())) {
                    if (logDTO.getTime().getMonth() == current.getMonth()) {
                        hasLog = true;
                        System.out.println(logDTO);
                    }
                }
            }
        }if(!hasLog) System.out.println("No Log"); 
    }
    public void Transfer(Bank a, Bank b, long m) {
        long result1, result2;
        result1 = b.getAmount() - m;
        b.setAmount(result1);
        Bank ac = searchAccountID(a.getId());
        result2 = ac.getAmount() + m;
        ac.setAmount(result2);
        logList.add(new Log(b.getId(), "Transfer to:"+ac.getId()+"-Money:"+m+"VND"+"-Balance:"+result1+"VND."));
        saveToFileLog("Log.dat");
        saveToFileBank("Bank.dat");
    }

    public boolean remove(String x, Bank a) {
        if (x.toUpperCase().equals("YES")) {
            int index = searchId(a.getId());
            User b = searchUserId(a.getId());
            Log c = searchLogId(a.getId());
            logList.remove(c);
            saveToFileLog("Log.dat");
            userList.remove(b);
            saveToFileUser("User.dat");
            this.remove(index);
            saveToFileBank("Bank.dat");
            System.out.println("Your account"+a.getId()+" had deleted");
        }
        return false;
    }

}
