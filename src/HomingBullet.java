import java.util.ArrayList;

import GUI.GUI;

public class HomingBullet extends Bullet {
    public double x = 0;
    public double y = 0;

    public double direction = 0;

    public double speed = 1;

    public int life = 0;

    public boolean dead = false;

    public ArrayList<BulletEffect> bulletEffects = new ArrayList<BulletEffect>(0);

    public HomingBullet(double x, double y, double direction, double speed){
        this.x = x;
        this.y = y;

        this.direction = direction;

        this.speed = speed;
    }
    public HomingBullet(double x, double y, double direction){
        this.x = x;
        this.y = y;

        this.direction = direction;
    }
    public HomingBullet(){
    }

    public void display(App app, Player player){
        this.x += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.y += Math.sin(Math.toRadians(this.direction)) * this.speed;

        double dp = Math.toDegrees(Math.atan2(player.y - this.y, player.x - this.x));

        System.out.println("r");

        this.direction = dp;

        app.fill(70);
        app.strokeWeight(0);

        app.ellipse((int)this.x, (int)this.y, 10, 10);

        if(this.x < 0 || this.x > 800 || this.y < 0 || this.y > 800){
            this.dead = true;
        }

        for(int i = bulletEffects.size()-1; i>=0; i--){
            bulletEffects.get(i).display(app);
        }

        if(app.frames % 3 == 0){
            bulletEffects.add(new BulletEffect(2 + (int)(this.x + 5 * Math.random()), 2 + (int)(this.y + 5 * Math.random())));
        }

        life++;
    }

    public static void main(String[] args){

    }
}

