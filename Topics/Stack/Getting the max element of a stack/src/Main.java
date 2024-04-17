import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<Integer> list = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> listSort = new ArrayDeque<>(list);
        int theSize = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < theSize;i++){
            ArrayList<String> input = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

            if(input.size() > 1){
                if("push".equals(input.get(0))){
                    list.offerLast(Integer.parseInt(input.get(1)));
                    if(list.size() == 1){
                        listSort.addLast(Integer.parseInt(input.get(1)));
                    } else if(Integer.parseInt(input.get(1)) > listSort.peekLast()){
                        listSort.offerLast(Integer.parseInt(input.get(1)));
                    } else {
                        listSort.offerLast(listSort.peekLast());
                    }
                }
            } else if("max".equals(input.get(0))) {
                // a faire
                System.out.println(listSort.peekLast());
            } else if("pop".equals(input.get(0)) && !list.isEmpty()){
                list.pollLast();
                listSort.pollLast();
            }
        }
    }
}