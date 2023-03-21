import Dictionary.Dictionary;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        Dictionary<String> dictionary = new Dictionary<String>();

        int type = -1, op = -1;
        int[] result;
        String key;

        while (type != 1 && type != 2) {
            System.out.println("Enter 1 for AVL, 2 for RB, 0 to exit");
            type = sc.nextInt();
            switch (type){
                case 0:
                    return;
                case 1:
                    dictionary.initialize("AVL");
                    break;
                case 2:
                    dictionary.initialize("RB");
                    break;
                default:
                    break;
            }
        }

        while (op != 0) {
            System.out.println("Enter 1 for search, 2 for insert, 3 for delete," +
                    " 4 for batch insert, 5 for batch delete," +
                    " 6 for tree size, 7 for tree height, 8 for inorder traversal, 9 for clear, 0 to exit");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Enter a string to search");
                    key = sc.next();
                    if(dictionary.search(key))
                        System.out.println("Exist");
                    else
                        System.out.println("Doesn't exist");
                    break;
                case 2:
                    System.out.println("Enter a string to insert");
                    key = sc.next();
                    if(dictionary.insert(key))
                        System.out.println("Inserted");
                    else
                        System.out.println("Already exist");
                    break;
                case 3:
                    System.out.println("Enter a string to delete");
                    key = sc.next();
                    if(dictionary.delete(key))
                        System.out.println("Exist");
                    else
                        System.out.println("Doesn't exist");
                    break;
                case 4:
                    System.out.println("Enter the file path");
                    key = sc.next();
                    result = dictionary.batchInsert(key);
                    System.out.println(result[0] + " Inserted");
                    System.out.println(result[1] + " Exist");
                    break;
                case 5:
                    System.out.println("Enter the file path");
                    key = sc.next();
                    result = dictionary.batchDelete(key);
                    System.out.println(result[0] + " Deleted");
                    System.out.println(result[1] + " Doesn't exist");
                    break;
                case 6:
                    System.out.println("Size: " + dictionary.size());
                case 7:
                    System.out.println("Height: " + dictionary.height());
                case 8:
                    System.out.print("Traversal: ");
                    dictionary.traverse();
                    break;
                case 9:
                    dictionary.clear();
                    System.out.println("Cleared");
                    break;
                default:
                    break;
            }
        }
    }
}
