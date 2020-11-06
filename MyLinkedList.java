public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail; //notes from classes
    int size = 0;
    E data;

    private class Node<E> {  //do I have to make it static?
        E data;
        Node<E> next;
        public Node(E item) {
            this.data = item;
            this.next = null;
        } //Constructor?
    }


    public static void main(String[] args)
    {
        MyLinkedList<String> mainlist = new MyLinkedList<String>();
        //mainlist.head = new Node("Owen");

        mainlist.add("Owen"); //if I do it this way like the notes, then in mylinkedlist(), I have to use the new node function instead of 'head = null'
        mainlist.add("Rory");                               //add() function works
        mainlist.add("Miranda", 2);            //add() with position works
        mainlist.add("Waffles");
        mainlist.printlist();

        System.out.println("=========================");
        System.out.println("removed: "+mainlist.remove(4)); //remove() works
        System.out.println("=========================");
        System.out.println("Fixed list:"); mainlist.printlist();



        //System.out.println(mainlist.reverse(mainlist.head));
        mainlist.reverse();

        mainlist.printlist();

    }

    public MyLinkedList()
    {
        //head = null;  //if I use this I get a nullexception.
        head = new Node<E>(null);  //notes from todays class.
        int size = 0;

    }

    public void add(E item)
    {
        Node prev = head;
        for (int i = 0; i < size; i++) {
            prev = prev.next;
        }
        Node node = new Node(item);
        prev.next = node;
        ++size;
    }

    public void add(E item, int position)
    {
        if (position == 0) {
            Node node = new Node<E>(item);
            node.next = head;
            head = node;
            ++size;
        } else {
            Node prev = head;
            for (int i = 0; i < position-1; i++) {
                prev = prev.next;
            }
            Node node = new Node(item);
            node.next = prev.next;
            prev.next = node;
            ++size;
        }
    }

    public E get(int position)
    {
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public E remove(int position)
    {
        if (position == 0)
        {
            Node<E> node = head;
            head = head.next;
            --size;
            return node.data;
        } else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next;
            }
            Node<E> node = prev.next;
            prev.next = node.next;
            --size;
            return node.data;
        }
    }

    public void reverse() {
        head.next = reverse(head); //
    }

    private Node reverse (Node node) {
        Node<E> prev = null;
        Node<E> current = head.next;
        Node<E> next;
        while (current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            node = prev;
        }
        return node;
    }

    /*public E reverse(Node<E> head)
    {
        Node<E> prev = null;                                 //Dont want to start at current=head, start at current=head.next
        Node<E> current = head.next;
        Node<E> next;
        while (current != null)
        {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }   //not head but head.next

        return prev.data;
    }*/

    public void printlist()
    {
        Node<E> n = head.next;
        while (n != null)
        {
            System.out.print(n.data+"->");
            n = n.next;
        }
        System.out.println();
    }




















}