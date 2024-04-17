import java.util.*;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> listInput = new ArrayList(Arrays.asList(scanner.nextLine().split("")));
        if (listInput.size() % 2 != 0) {
            System.out.println("false");
            return;
        } else {
            Deque<String> listFilter = new ArrayDeque();
            
            for (String s : listInput) {
                
                if ("(".equals(s)
                    || "{".equals(s)
                    || "[".equals(s)){
                    listFilter.addLast(s);
                } else if (!listFilter.isEmpty()) {
                        
                    if (")".equals(s) && "(".equals(listFilter.peekLast()) 
                          || "}".equals(s) && "{".equals(listFilter.peekLast())
                          || "]".equals(s) && "[".equals(listFilter.peekLast())) {
                          
                        listFilter.pollLast();
                    } else {
                        
                        System.out.println("false");
                        return;
                    }  
                } else {
                    System.out.println("false");
                    return; 
                }
        
            }
            System.out.println("true");
        
        }
    }
}
