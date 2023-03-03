
// is there any shared behavior I can 
// put into a parent class?

public class SquareDeflector {
    public int x;
    public int y;

    public int width = 200;
    public int height = 200;

    public int life = 0;
    public int maxLife = 500;

    public boolean dead = false;

    public SquareDeflector(int x, int y, int width, int height){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    void display(App app){
        app.fill(0, 0);

        app.stroke(70, life < 70 ? life : (
            life > (maxLife - 70) ?
            70 - life + (maxLife - 70) :
            70
        ));
        app.strokeWeight(5);

        app.rect(this.x - this.width/2, this.y - this.height/2, this.width, this.height);

        this.life++;

        if(this.life > maxLife){
            this.dead = true;
        }
    }
}
