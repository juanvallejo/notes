/*
Chapter 3
*/
import java.util.ArrayList;

public class cs270 {
    public static void count(int i,int n) {
        if(i == n) {
           System.out.println(n);
        } else {
            System.out.println(i);    
            i++;
            count(i,n);
        }
    }
    public static int add(int start,int end) {
        if(start + 1 == end) {
            return start+end;
        } else {
            return start + add(++start,end);
        }
    }
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("milk");
        test.add("butter");
        test.add("eggs");
        test.add("waffles");
        
        test.get(1) = "fuck";

        for(int i=0;i<test.size();i++) {
            System.out.println(test.get(i));
        }
//        System.out.println(add(1,7));
    }
}

/*
Chapter 4

public void swap(aList, i , j) {
    int temp = i;
    aList.set(i,j);
    aList.set(j,temp);
}
*/
