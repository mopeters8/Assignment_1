import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class NBody extends JPanel implements ActionListener
{
    private final Timer tim = new Timer(50, this);
    private final ArrayList<Planet> pContent = new ArrayList<>();  //need to change this. arraylist of planets
    private final double scale = 10000000.0;
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

        //GET & SET FUNCTIONS


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

    public void printNBody(Planet planet)
    {
        System.out.println(planet.getName());
        System.out.println(planet.getnMass());
        System.out.println(planet.getxCoord());
        System.out.println(planet.getyCoord());
        System.out.println(planet.getxVel());
        System.out.println(planet.getyVel());
        System.out.println(planet.getpixelSize());
    }

    public void paintComponent(Graphics g)
    {
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

    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < pContent.size(); i++) {
            for (int j = 0; j < pContent.size(); j++) {
                if (i != j)
                {
                    Planet nextplanet = pContent.get(j);
                    Planet planet = pContent.get(i);
                    double force = pMotion(pContent.get(i), pContent.get(j)) / scale / planet.getnMass();
                    double xForce = force;
                    double yForce = force;

                    //System.out.println(i+" Force: "+Force);
                    if (planet.getxCoord() > nextplanet.getxCoord())
                    {
                        xForce = -xForce;
                    }
                    if (xForce == 0)
                    {
                        xForce = force;
                    }
                    planet.setxVel(planet.getxVel() + xForce);

                    //System.out.println(i+" newVel: "+xForce/planet.getnMass());  //for testing

                    if (planet.getyCoord() > nextplanet.getyCoord())
                    {
                        yForce = -yForce;
                    }
                    if (yForce == 0)
                    {
                        yForce = force;
                        planet.setyVel(planet.getyVel() + yForce);

                    }
                    else
                    {
                        planet.setyVel(planet.getyVel() + yForce);
                    }

                    planet.setxCoord((int) (planet.getxCoord()+planet.getxVel()));
                    planet.setyCoord((int) (planet.getyCoord()+planet.getyVel()));

                }
            }
        }
        repaint();
    }

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
        NBody addP = new NBody();

        String line;
        String listType = "";
        Double distanceType = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(nbFile));  //buffered reader for CSV
            listType = br.readLine();
            distanceType = Double.parseDouble(br.readLine()); //mass in kg
            while((line = br.readLine()) != null)
            {
                String[] content = line.split(",");  //read first two lines, then in loop read and split.
                addP.planetadd(content[0], content[1], content[2], content[3], content[4], content[5], content[6]);     //THIS IS WHERE I AM USING MY ADD FUNCTION.
            }
        } catch (IOException e) {
            System.out.println("oops");
        }

        System.out.println("--------------\nList type: "+listType);
        System.out.println("Distance: "+distanceType);

        for (int i = 0; i < addP.pContent.size(); i++) {
            System.out.println("--------------");
            addP.printNBody(addP.planetget(i));                         //THIS IS WHERE I AM USING MY GET FUNCTION.
        }

        JFrame jf = new JFrame();

        jf.setTitle("NBody!");
        jf.setSize(768,768);
        jf.add(addP);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
