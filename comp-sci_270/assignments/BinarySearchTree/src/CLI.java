import java.util.Scanner;
public class CLI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String next = "";
           
        while(!next.equals("bye")) {
            next = in.nextLine();
            String[] cmd = next.split(" ");
           
            if(next.equals("bye")) {
                System.out.println("Exiting...");
            } else if(cmd[0].equals("insert")) {
                try {
                    Person person = new Person(cmd[1],cmd[2],Integer.parseInt(cmd[3]),cmd[4]);
                    BstObj bst = new BstObj();
                    bst.insert(person);
                    
                    System.out.println(cmd[1]+" "+cmd[2]+" has been inserted into the database.");
                } catch(Exception e) {
                    System.out.println("This command takes 4 arguments.");
                }
            } else {
               System.out.println(">"+next);
            }
        }
    }
}