import java.util.ArrayList;

import GUI.Pair;

public class Bullet {
    public double x = 0;
    public double y = 0;

    public double direction = 0;

    public double speed = 1;

    public int life = 0;

    public boolean dead = false;

    public int type = 0;

    public ArrayList<BulletEffect> bulletEffects = new ArrayList<BulletEffect>(0);

    public Bullet(double x, double y, double direction, double speed){
        this.x = x;
        this.y = y;

        this.direction = direction;

        this.speed = speed;
    }
    public Bullet(double x, double y, double direction){
        this.x = x;
        this.y = y;

        this.direction = direction;
    }
    public Bullet(){
    }

    public Bullet(double x, double y, double direction, double speed, int type){
        this.x = x;
        this.y = y;

        this.direction = direction;

        this.speed = speed;

        this.type = type;
    }
    public Bullet(double x, double y, double direction, int type){
        this.x = x;
        this.y = y;

        this.direction = direction;

        this.type = type;
    }

    public void display(App app, Player player){
        this.x += Math.cos(Math.toRadians(this.direction)) * this.speed;
        this.y += Math.sin(Math.toRadians(this.direction)) * this.speed;

        if(this.type == 1){
            double dp = Math.atan2(player.y + 25 - this.y, player.x + 25 - this.x);

            this.direction = Math.toDegrees(dp);
        }
        

        app.fill(70, Math.max(0, Math.min(255, 500 - this.life)));
        app.strokeWeight(0);

        if(this.life > 500){
            this.dead = true;
        }

        if(this.type == 1){
            app.ellipse((int)this.x, (int)this.y, 10, 10);
        } else {
            ArrayList<Pair<String, double[]>> saved = app.getTransformations();

            app.translate(this.x + 5, this.y + 5);
            app.rotate((double)this.life/10);
            app.rect(-5, -5, 10, 10);

            app.setTransformations(saved);
        }

        // System.out.println("e");

        if(this.x < 0 || this.x > 800 || this.y < 0 || this.y > 800){
            this.dead = true;
        }

        for(int i = bulletEffects.size()-1; i>=0; i--){
            bulletEffects.get(i).display(app);
        }

        if(app.frames % 3 == 0){
            bulletEffects.add(new BulletEffect(2 + (int)(this.x + 5 * Math.random()), 2 + (int)(this.y + 5 * Math.random()), Math.max(0, Math.min(255, 500 - this.life))));
        }

        life++;
    }

    public static void main(String[] args){

    }
}
