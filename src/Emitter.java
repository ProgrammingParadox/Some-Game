import java.util.ArrayList;
import java.lang.Math;
import GUI.GUI;

public class Emitter {
    public int x;
    public int y;

    public ArrayList<CircleEffect> circleEffects = new ArrayList<CircleEffect>(0);

    public boolean dead = false;

    public Emitter(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void addCircleEffect(int x, int y, int s){
        if(circleEffects == null) return;

        circleEffects.add(new CircleEffect(x, y, s));
    }

    public void drawCircleEffects(GUI app){
        if(circleEffects == null){ return; }

        for(int i = circleEffects.size()-1; i>=0; i--){
            circleEffects.get(i).display(app);

            if(circleEffects.get(i).dead){
                circleEffects.remove(i);
            }
        }
    }

    public boolean justAdded = false;
    public void display(App app){
        drawCircleEffects(app);

        app.fill(70);
        app.ellipse(this.x - 25, this.y - 25, 50, 50);

        if(app.frames % Math.floor(app.bulletRate) == 0){ 
            app.addBullet((double)this.x - 5, (double)this.y - 5, Math.random() * 360, Math.ceil(app.bulletRate/100) * 2, 0);

            addCircleEffect(this.x, this.y, 50);

            app.score++;
        }

        if(app.frames % Math.floor(app.bulletRate * 10) == 0){ 
            app.addBullet((double)this.x - 5, (double)this.y - 5, Math.random() * 360, Math.ceil(app.bulletRate/100) * 2, 1);

            addCircleEffect(this.x, this.y, 50);

            app.score += 5;
        }
    }
}
