import java.util.Scanner;

class Node {
    String data;
    Node next;

    Node(String value) {
        this.data = value;
        this.next = null;
    }
}

public class App{
    public static Node createNodesFromString(String s) {
        String[] words = s.split(" ");
        Node first = new Node(words[0]);

        for (int i = 1; i < words.length; ++i) {
            Node ref = first;
            while (ref.next != null) {
                ref = ref.next;
            }
            ref.next = new Node(words[i]);
        }

        Node head = first;

        return head;
    }

    public static void printNodes(Node head) {
        Node buffer = head;
        while (buffer != null) {
            System.out.print(buffer.data + " ");
            buffer = buffer.next;
        }
        System.out.println();
    }

    public static Node deleteCustom(Node head, int index) {
        Node buffer = head;
        for (int i = 0; i < index; i++) {
            buffer = buffer.next;
        }
        buffer.next = buffer.next.next;
        return head;
    }

    public static Node deleteLast(Node head) {
        Node ref = head;
        while (ref.next.next != null) {
            ref = ref.next;
        }
        ref.next = null;
        return head;
    }

    public static Node deleteFirst(Node head) {
        head = head.next;
        return head;
    }

    public static Node addElement(Node head, int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элемент для вставки: ");
        String value = scanner.nextLine();
        Node newNode = new Node(value);

        index--;
        Node buffer = head;
        for (int i = 0; i < index; i++) {
            buffer = buffer.next;
        }
        Node tmp = buffer.next;
        newNode.next = tmp;
        buffer.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите элементы списка: ");
        String str = scanner.nextLine();
        Node head = createNodesFromString(str);
        int index;
        while (true) {
            System.out.print(
                    "Выберите действие:\n1 - Добавить элемент в список\n2 - удалить первый элемент\n3 - удалить последний элемент\n4 - удалить другой элемент\n5 - выход\nВаш выбор: ");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    System.out.print("\nВведите индекс для вставки: ");
                    try {
                        index = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.print("Ошибочный индекс");
                        return;
                    }
                    head = addElement(head, index);
                    System.out.println("Новый список: ");
                    printNodes(head);
                    break;
                case 2:
                    head = deleteFirst(head);
                    System.out.println("Новый список: ");
                    printNodes(head);
                    break;
                case 3:
                    head = deleteLast(head);
                    System.out.println("Новый список: ");
                    printNodes(head);
                    break;
                case 4:
                    System.out.print("\nВведите индекс (начиная с 2) для удаления: ");
                    try {
                        index = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.print("Ошибочный индекс");
                        return;
                    }
                    if (index < 2) {
                        System.out.print("Неверное значение");
                        return;
                    }
                    head = deleteCustom(head, index - 1);
                    System.out.println("Новый список: ");
                    printNodes(head);
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    scanner.close();
                    return;
            }
        }
    }
}
