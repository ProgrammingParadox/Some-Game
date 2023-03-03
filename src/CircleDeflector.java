
public class CircleDeflector {
    public int x;
    public int y;

    public int radius = 200;

    public int life = 0;
    public int maxLife = 500;

    public boolean dead = false;

    public CircleDeflector(int x, int y, int radius){
        this.x = x;
        this.y = y;

        this.radius = radius;
    }

    void display(App app){
        app.fill(0, 0);

        app.stroke(70, life < 70 ? life : (
            life > (maxLife - 70) ?
            70 - life + (maxLife - 70) :
            70
        ));
        app.strokeWeight(5);

        app.ellipse(this.x - this.radius, this.y - this.radius, this.radius*2, this.radius*2);

        // app.ellipse(this.x, this.y, 5, 5);

        this.life++;

        if(this.life > this.maxLife){
            this.dead = true;
        }
    }
}
