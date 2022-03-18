import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class fisher {

    public static void main(String[] args) throws AWTException, InterruptedException {
	    new fisher();

    }
    fisher() throws AWTException, InterruptedException {
        //rectangle for bobber finding
        Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        //rect.setSize(size);

        //rectangle for hooked find
        Rectangle tinyRect = new Rectangle(50,50);



        Random rand = new Random();
        System.out.println("Hello I am robot.");
        Robot robo = new Robot();
        System.out.print("Robot starting in ");
        int r,g,b,x = 0,y = 0,color;

        Color c;
        for(int i = 3;i>0;i--){
            System.out.print(i+" ");
            Thread.sleep(1000);
        }

        while(true) {
            System.out.println("Casting!");
            robo.keyPress(KeyEvent.VK_CONTROL);
            keyPressDelay();
            robo.keyPress(KeyEvent.VK_D);
            keyPressDelay();
            int coinFlip = rand.nextInt(1);
            if (coinFlip == 1) {
                robo.keyRelease(KeyEvent.VK_D);
                keyPressDelay();
                robo.keyRelease(KeyEvent.VK_CONTROL);
            } else {
                robo.keyRelease(KeyEvent.VK_CONTROL);
                keyPressDelay();
                robo.keyRelease(KeyEvent.VK_D);
            }

            robo.keyRelease(KeyEvent.VK_D);
            keyPressDelay();
            robo.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2200);
            BufferedImage buff;

            buff = robo.createScreenCapture(rect);
            buff.flush();
            int counter = 0;
            boolean found = false;
            System.out.println("Searching..");
            for (int i = 1550; i < 2050; i++) {
                for (int j = 250; j < 500; j++) {

                    counter++;
                    color = buff.getRGB(i, j);
                    c = new Color(color);
                    r = c.getRed();
                    g = c.getGreen();
                    b = c.getBlue();
                    //System.out.println(counter + " R:" + r + " G:" + g + " B:" + b);

                    if ((r > 72 && g > 81 && b > 88) && (r < 100 && g < 100 && b < 100)) {
                        //robo.mouseMove(i, j);
                        x=i;
                        y=j;
                        System.out.println("Bobber found" + " R:" + r + " G:" + g + " B:" + b);
                        found = true;
                        break;
                    }
                }
                if(found==true){break;}
            }
            if(found == false){
                System.out.println("Bobber not found, restarting");
                Thread.sleep(rand.nextInt(3500));
                continue;
            }
            counter=0;
            boolean caught = false;
            System.out.println("Awaiting bite");
            while(caught==false){
                System.out.print(" .");

                buff = robo.createScreenCapture(rect);
                buff = buff.getSubimage(x-20,y-20,40,40);
                for(int i=0;i<40;i++){
                    for(int j=0;j<40;j++){
                        if(buff.getRGB(i,j)==-1){
                            System.out.println("Fish caught!");
                            robo.mouseMove(x,y);
                            keyPressDelay();
                            keyPressDelay();
                            robo.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                            keyPressDelay();
                            robo.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                            caught=true;
                            break;

                        }
                        if(caught==true){
                            break;
                        }
                    }
                }
                /*
                for(int i=x-20;i<x+15;i+=2){
                    for(int j=y-20;j<y+15;j+=2){
                        if(buff.getRGB(i,j)==-1){
                            System.out.println("Fish caught!");
                            robo.mouseMove(x,y);
                            keyPressDelay();
                            keyPressDelay();
                            robo.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                            keyPressDelay();
                            robo.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                            caught=true;
                            break;

                        }
                        if(caught==true){
                            break;
                        }
                    }
                }

                 */

                if(caught == false){
                    //Thread.sleep(250);
                   // counter++;
                    if(counter == 60){
                        System.out.println("Didn't find the fish :(");
                        break;
                    }
                }
                Thread.sleep(rand.nextInt(5000));
                
            }
        }


    }

    private void keyPressDelay() throws InterruptedException {
        Random rand = new Random();
        int i = rand.nextInt(450),j = rand.nextInt(50);
        if(i>j){
            Thread.sleep(i-j);
        }else{
            Thread.sleep(j-i);
        }

    }

    private void slideMouse(){

    }

}
