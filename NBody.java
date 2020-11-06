import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class NBody<P> extends JPanel implements ActionListener
{
    private String ListType = "";
    private double scale = 0; //add scale

////////////////////////////////////
//    ARRAY LIST BELOW
////////////////////////////////////
    private final Timer tim = new Timer(50, this);
    private final ArrayList<Planet> pContent = new ArrayList<>();  //need to change this. arraylist of planets

    private final double G = 6.67;

    public class Planet {
        private final String Name;
        private final int pixelSize;
        private final double nMass;
        private double xVel;
        private double yVel;
        private double xCoord;
        private double yCoord;
        private final Color col;

        public void setListType(String type) {
            ListType = type;
        }

        public void setScale(Double newScale) {
           scale = newScale;
        }

        public Planet(String sName, String sMass, String sXCoord, String sYCoord, String sXVel, String sYVel, String sPix)
        {
            Name = sName;
            nMass = Double.parseDouble(sMass);
            xCoord = Double.parseDouble(sXCoord);
            yCoord = Double.parseDouble(sYCoord);
            xVel = Double.parseDouble(sXVel);
            yVel = Double.parseDouble(sYVel);
            pixelSize = Integer.parseInt(sPix);
            col = setColor();
        }

////////////////////////////////////
        //GET & SET FUNCTIONS
////////////////////////////////////

        public Color getColor() { return col; }
        public Color setColor() {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color col = new Color(r, g, b);
            return col;
        }
        public String getName() {
            return Name;
        }
        public double getnMass() { return nMass;}
        public double getxCoord() {
            return xCoord;
        }
        public double getyCoord(){
            return yCoord;
        }
        public double getxVel() {
            return xVel;
        }
        public double getyVel() {
            return yVel;
        }
        public int getpixelSize() {
            return pixelSize;
        }
        public void setxCoord(int newX) {
            xCoord = newX;
        }
        public void setyCoord(int newY) {
            yCoord = newY;
        }
        public void setxVel(double newxVel) { xVel = newxVel;}
        public void setyVel(double newyVel) { yVel = newyVel;}
    }
    public void planetadd(String sName, String sMass, String sXCoord, String sYCoord, String sXVel, String sYVel, String sPix) {
        Planet p = new Planet(sName, sMass, sXCoord, sYCoord, sXVel, sYVel, sPix);
        pContent.add(p);
    }
    public Planet planetget(int pos)
    {
        return pContent.get(pos);
    }

    public void printArrayList(Planet planet)
    {
        System.out.println(planet.getName());
        System.out.println(planet.getnMass());
        System.out.println(planet.getxCoord());
        System.out.println(planet.getyCoord());
        System.out.println(planet.getxVel());
        System.out.println(planet.getyVel());
        System.out.println(planet.getpixelSize());
    }


////////////////////////////////////
//     LINKED LIST BELOW
////////////////////////////////////

    private Node<P> head;
    private Node<P> tail;
    int linkedlistsize = 0;

    public class Node<P> {
        String nName;
        String nMass;
        String nXCoord;
        String nYCoord;
        String nXVel;
        String nYVel;
        String nPix;
        Node<P> next;
        public Node(String Name, String Mass, String XCoord, String YCoord, String XVel, String YVel, String Pix)
        {
            this.nName = Name;
            this.nMass = Mass;
            this.nXCoord = XCoord;
            this.nYCoord = YCoord;
            this.nXVel = XVel;
            this.nYVel = YVel;
            this.nPix = Pix;
            this.next = null;
        }
    }

    public NBody() {
        head = new Node<P>(null, null, null, null, null, null, null);
        //head = null;
        int size = 0;
    }

    public void add(String Name, String Mass, String XCoord, String YCoord, String XVel, String YVel, String Pix)
    {
        Node prev = head;
        for (int i = 0; i < linkedlistsize; i++) {
            prev = prev.next;
        }
        Node node = new Node(Name, Mass, XCoord, YCoord, XVel, YVel, Pix);
        prev.next = node;
        ++linkedlistsize;
    }

////////////////////////////////////
//     SET METHODS FOR LINKED LIST BELOW
////////////////////////////////////

    public void setListType(String type) {
        ListType = type;
    }

    public void setScale(Double newScale) {
        scale = newScale;
    }

    public void setLinkedXCoord(int position, Double newX)
    {
        Node<P> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        String str = String.valueOf(newX);
        curr.nXCoord = str;
    }

    public void setLinkedYCoord(int position, Double newY)
    {
        Node<P> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        String str = String.valueOf(newY);
        curr.nYCoord = str;
    }

    public void setLinkedXVel(int position, Double newX)
    {
        Node<P> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        String str = String.valueOf(newX);
        curr.nXVel = str;
    }

    public void setLinkedYVel(int position, Double newY)
    {
        Node<P> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        String str = String.valueOf(newY);
        curr.nYVel = str;
    }

////////////////////////////////////
//     GET METHODS FOR LINKED LIST BELOW
////////////////////////////////////

    public int getLinkedlistSize() {
        return linkedlistsize;
    }

    public double getLinkedXCoord(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nXCoord);
    }

    public double getLinkedYCoord(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nYCoord);
    }

    public double getLinkedMass(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nMass);
    }

    public double getLinkedXVel(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nXVel);
    }

    public double getLinkedYVel(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nYVel);
    }

    public double getLinkednPixel(int position)
    {
        Node<P> curr = head.next;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        //double getxCoord = Double.parseDouble(curr.nXCoord);
        return Double.parseDouble(curr.nPix);
    }

    public void printLinkedList()  //printing working
    {
        Node<P> n = head.next;
        while (n != null)
        {
            System.out.println("--------------");
            System.out.println(n.nName);
            System.out.println(n.nMass);
            System.out.println(n.nXCoord);
            System.out.println(n.nYCoord);
            System.out.println(n.nXVel);
            System.out.println(n.nYVel);
            System.out.println(n.nPix);
            n = n.next;
        }
        System.out.println();
    }


/////////////////////////////////////
//     PRINT FUNCTIONS & MAIN
////////////////////////////////////


    public void paintComponent(Graphics g)
    {
        if (ListType.equals("ArrayList")) {
            super.paintComponent(g);
            for (int i = 0; i < pContent.size(); i++) {
                Planet currplanet = pContent.get(i);
                int xC = (int) (currplanet.getxCoord());
                int yC = (int) (currplanet.getyCoord());
                g.setColor(currplanet.getColor());
                g.fillOval(xC, yC, currplanet.getpixelSize(), currplanet.getpixelSize());
            }
            tim.start();
        }

        else {
            super.paintComponent(g);
            for (int i = 0; i < linkedlistsize; i++) {
                double xC = getLinkedXCoord(i);
                double yC = getLinkedYCoord(i);
                double pixelsize = getLinkednPixel(i);

                int newX = (int) xC;
                int newY = (int) yC;
                int newpix = (int) pixelsize;

                g.setColor(Color.blue);
                g.fillOval(newX, newY, newpix, newpix);
            }
            tim.start();
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (ListType.equals("ArrayList")) {
            for (int i = 0; i < pContent.size(); i++) {
                for (int j = 0; j < pContent.size(); j++) {
                    if (i != j) {
                        Planet nextplanet = pContent.get(j);
                        Planet planet = pContent.get(i);
                        double force = pMotion(pContent.get(i), pContent.get(j)) / scale / planet.getnMass();
                        double xForce = force;
                        double yForce = force;

                        //System.out.println(i+" Force: "+Force);
                        if (planet.getxCoord() > nextplanet.getxCoord()) {
                            xForce = -xForce;
                        }
                        if (xForce == 0) {
                            xForce = force;
                        }
                        planet.setxVel(planet.getxVel() + xForce);

                        //System.out.println(i+" newVel: "+xForce/planet.getnMass());  //for testing

                        if (planet.getyCoord() > nextplanet.getyCoord()) {
                            yForce = -yForce;
                        }
                        if (yForce == 0) {
                            yForce = force;
                            planet.setyVel(planet.getyVel() + yForce);

                        } else {
                            planet.setyVel(planet.getyVel() + yForce);
                        }

                        planet.setxCoord((int) (planet.getxCoord() + planet.getxVel()));
                        planet.setyCoord((int) (planet.getyCoord() + planet.getyVel()));

                    }
                }
            }
        }

        else {
            for (int i = 0; i < getLinkedlistSize(); i++) {
                for (int j = 0; j < getLinkedlistSize(); j++) {
                    if (i != j) {
                        double force = nMotion(getLinkedXCoord(i), getLinkedXCoord(j), getLinkedMass(i), getLinkedMass(j), getLinkedYCoord(i), getLinkedYCoord(j)) / scale / getLinkedMass(i);
                        double xForce = force;
                        double yForce = force;

                        if (getLinkedXCoord(i) > getLinkedXCoord(j)) {
                            xForce = -xForce;
                        }
                        if (xForce == 0) {
                            xForce = force;
                        }
                        setLinkedXVel(i, xForce);

                        if (getLinkedXCoord(i) > getLinkedYCoord(j)) {
                            yForce = -yForce;
                        }
                        if (yForce == 0) {
                            yForce = force;
                        }
                        setLinkedYVel(i, yForce);

                        setLinkedXCoord(i, (getLinkedXCoord(i)+getLinkedXVel(i)));
                        setLinkedYCoord(i, (getLinkedYCoord(i)+getLinkedYVel(i)));

                    }
                }
            }
        }
        repaint();
    }

    //For linkedlist
    public double nMotion(Double p1xCoord, Double p2xCoord, Double p1nMass, Double p2nMass, Double p1yCoord, Double p2yCoord) {
        double newMass = p1nMass * p2nMass;
        double newG = G * newMass;
        double xDistance = Math.pow(p1xCoord - p2xCoord, 2);
        double yDistance = Math.pow(p1yCoord - p2yCoord, 2);
        double distance = (xDistance - yDistance);
        return (newG / distance );
    }

    //For arraylist
    public double pMotion(Planet p1, Planet p2) {

        double newMass = p1.getnMass() * p2.getnMass(); //CORRECT
        double newG = G * newMass; //CORRECT
        double xDistance = Math.pow(p1.getxCoord() - p2.getxCoord(), 2); //CORRECT
        double yDistance = Math.pow(p1.getyCoord() - p2.getyCoord(), 2); //CORRECT
        double distance = ( xDistance + yDistance ); //CORRECT
        return (newG / distance);
    }

    public static void main(String[] args) {
        File nbFile = new File("src/nbody_input.txt");
        NBody<String> addP = new NBody<String>();

        String line;
        String listType = "";
        Double distance = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(nbFile));  //buffered reader for CSV
            listType = br.readLine();
            distance = Double.parseDouble(br.readLine()); //mass in kg
            addP.setScale(distance);

            if (listType.equals("ArrayList")) {
                while ((line = br.readLine()) != null) {
                    addP.setListType(listType);
                    String[] content = line.split(",");  //read first two lines, then in loop read and split.
                    addP.planetadd(content[0], content[1], content[2], content[3], content[4], content[5], content[6]);     //THIS IS WHERE I AM USING MY ADD FUNCTION.
                    System.out.println("[X] Added for Arraylist! ");
                }
            }

            else if(listType.equals("LinkedList")) {
                while ((line = br.readLine()) != null) {
                    String[] content = line.split(",");  //read first two lines, then in loop read and split.
                    addP.add(content[0], content[1], content[2], content[3], content[4], content[5], content[6]);
                    System.out.println("[X] Added for LinkedList! ");

                }
            }

        } catch (IOException e) {
            System.out.println("oops");
        }

        System.out.println("--------------\nList type: "+listType);
        System.out.println("Distance: "+distance);


        addP.printLinkedList();                                         //printing Linked list

        for (int i = 0; i < addP.pContent.size(); i++) {                //printing arraylist
            System.out.println("--------------");
            addP.printArrayList(addP.planetget(i));                         //THIS IS WHERE I AM USING MY GET FUNCTION.
        }

        JFrame jf = new JFrame();

        jf.setTitle("NBody!");
        jf.setSize(768,768);
        jf.add(addP);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
