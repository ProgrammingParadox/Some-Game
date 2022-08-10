package GUI;

public class Button {
    public String label;

    public int x;
    public int y;

    public int w;
    public int h;

    public double a = 0;

    public Function onClick;

    public boolean justClicked = false;

    public Button(String label, int x, int y, int w, int h, Function onClick){
        this.label = label;

        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;

        this.onClick = onClick;
    }

    private double lerp(double a, double b, double t){
        return (1 - t) * a + t * b;
    }

    public boolean mouseIn(GUI app){
        return app.mouse.x > this.x && app.mouse.x < this.x + this.w &&
               app.mouse.y > this.y && app.mouse.y < this.y + this.h;
    }

    public void display(GUI app){
        if(mouseIn(app)){
            a = lerp(a, 1.0, 0.2);
        } else {
            a = lerp(a, 0.0, 0.2);
        }

        if(app.mouse.click && mouseIn(app)){
            onClick.function();
        }

        app.fill(250);

        app.stroke(70);
        app.strokeWeight(5);

        app.rect((int) (this.x - a * 5), (int) (this.y - a * 5), (int) (this.w + a * 10), (int) (this.h + a * 10));

        app.fill(70);
        app.fontSize((int) (20 + a * 2));
        app.text(this.label, (int) (this.x + 20 + a * 10), this.y + this.h/2 + 7);
    }

    public static void main(String[] args){

    }
}
