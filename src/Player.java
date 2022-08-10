import GUI.*;
import java.awt.event.*;

public class Player {
    public double x = 0;
    public double y = 0;

    public double xv = 0;
    public double yv = 0;

    public double speed = 6;

    public Player(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Player(){

    }

    public boolean inBullet(Bullet bullet){
        return bullet.x + 10 > this.x && bullet.x < this.x + 50 &&
               bullet.y + 10 > this.y && bullet.y < this.y + 50;
    }

    public void display(GUI app){
        this.x += this.xv;
        this.y += this.yv;

        this.xv *= 0.9;
        this.yv *= 0.9;

        if(this.x < 0 || this.x + 50 > 800){
            this.xv *= -1;

            if(this.x < 0){
                this.x = 0;
            } else {
                this.x = 750;
            }
        }
        if(this.y < 0 || this.y + 50 > 800){
            this.yv *= -1;

            if(this.y < 0){
                this.y = 0;
            } else {
                this.y = 750;
            }
        }

        app.fill(70);

        app.stroke(0, 0);
        app.strokeWeight(0);

        app.rect((int)this.x, (int)this.y, 50, 50);

        if(app.keys.keys[KeyEvent.VK_UP]){
            this.yv = -speed;
        }
        if(app.keys.keys[KeyEvent.VK_DOWN]){
            this.yv = speed;
        }
        if(app.keys.keys[KeyEvent.VK_LEFT]){
            this.xv = -speed;
        }
        if(app.keys.keys[KeyEvent.VK_RIGHT]){
            this.xv = speed;
        }
    }

    public static void main(String[] args){

    }
}
