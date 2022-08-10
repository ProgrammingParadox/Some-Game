import GUI.GUI;

public class BulletEffect {
    public int x;
    public int y;

    public double fade = 255;

    public boolean dead = false;

    public BulletEffect(int x, int y){
        this.x = x;
        this.y = y;
    }
    public BulletEffect(int x, int y, int fade){
        this.x = x;
        this.y = y;

        this.fade = fade;
    }

    public void display(GUI app){
        if(fade <= 0){
            this.dead = true;

            return;
        }

        app.fill(70, (int)fade);

        app.strokeWeight(0);

        app.ellipse(x - 1, y - 1, 2, 2);

        fade -= 15;
    }
}
