import GUI.GUI;

public class CircleEffect {
    public int x;
    public int y;

    public double size;

    public double fade = 255;

    public boolean dead = false;

    public CircleEffect(int x, int y, double size){
        this.x = x;
        this.y = y;

        this.size = size;
    }

    public void display(GUI app){
        if(fade <= 0){
            this.dead = true;

            return;
        }

        app.fill(0, 0);

        app.stroke(70, (int)fade);
        app.strokeWeight(5);

        app.ellipse(x - (int)size/2, y - (int)size/2, (int)size, (int)size);

        size += 5;
        fade -= 20;
    }

    public static void main(String[] args){

    }
}
