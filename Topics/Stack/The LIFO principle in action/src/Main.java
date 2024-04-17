import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Iterator;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> list = new ArrayDeque(length);
        for (int i = 0; i < length; i++) {
            list.addFirst(Integer.parseInt(scanner.nextLine()));
        }
        Iterator iteratorList = list.iterator();
        while (iteratorList.hasNext()) {
            System.out.println(iteratorList.next());
        }
        
        
    }
}
