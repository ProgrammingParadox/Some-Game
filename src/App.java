import java.util.ArrayList;
import GUI.*;

// c:; cd 'c:\Users\spott\Documents\Joshy\programming\Some Game\Some Game'; & 'C:\Program Files\Java\jdk-18.0.1.1\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Users\spott\Documents\Joshy\programming\Some Game\Some Game\bin' 'App' 

// so...
/*
 
    Idea for a thing

    Todo/ideas

    force fields
        - maybe later make bullets bounce off of 
          them? Probably needs some vector 
          algerbra, which I do not have memorized...

    Circle bullets will "explode" or "go poof" on
    impact with a square one

    Circling/going all the way around a bullet 
    somewhat closely gives points and destroys 
    bullets?

    Being able to destroy a emitter by directing a
    projectile at it

    Pressing the "/" key (or "q" key if wasd keys 
    added) releases some sort of emp blast that 
    clears bullets within a certain radius; has a
    cooldown

    Graph and stats on scores

 */

public class App extends GUI {
    public Player player;

    public ArrayList<Bullet> bullets = new ArrayList<Bullet>(0);

    public String scene = "home";

    public int frames = 1;

    public double bulletRate = 30;

    public int score = 0;

    public ArrayList<Emitter> emitters = new ArrayList<Emitter>(0);

    public ArrayList<SquareDeflector> squareDeflectors = new ArrayList<>(0);
    public ArrayList<CircleDeflector> circleDeflectors = new ArrayList<>(0);

    public App(){
        super("Some game", 800, 800);

        this.player = new Player(50, 50);

        emitters.add(new Emitter(400, 400));
    }

    public void addBullet(double x, double y, double d, int type){
        if(this.bullets == null) return;

        bullets.add(new Bullet(x, y, d, type));
    }
    public void addBullet(double x, double y, double d, double s, int type){
        if(this.bullets == null) return;

        bullets.add(new Bullet(x, y, d, s, type));
    }
    
    public void addEmitter(int x, int y){
        if(this.emitters == null) return;

        emitters.add(new Emitter(x, y));
    }

    public void addSquareDeflector(int x, int y, int width, int height){
        if(this.squareDeflectors == null) return;

        squareDeflectors.add(new SquareDeflector(x, y, width, height));
    }
    public void addCircleDeflector(int x, int y, int radius){
        if(this.circleDeflectors == null) return;

        circleDeflectors.add(new CircleDeflector(x, y, radius));
    }

    public void drawBullets(){
        if(bullets == null){ return; }

        for(int i = bullets.size()-1; i>=0; i--){
            bullets.get(i).display(this, player, this.squareDeflectors, this.circleDeflectors);

            if(player.inBullet(bullets.get(i))){
                scene = "dead";
            }

            if(bullets.get(i).dead){
                bullets.remove(i);
            }
        }
    }
    public void drawEmitters(){
        if(emitters == null){ return; }

        for(int i = emitters.size()-1; i>=0; i--){
            emitters.get(i).display(this);

            if(emitters.get(i).dead){
                emitters.remove(i);
            }
        }
    }

    public void drawSquareDeflectors(){
        if(squareDeflectors == null) return;

        for (int i = squareDeflectors.size()-1; i>=0; i--) {
            squareDeflectors.get(i).display(this);

            if(squareDeflectors.get(i).dead){
                squareDeflectors.remove(i);
            }
        }
    }
    public void drawCircleDeflectors(){
        if(circleDeflectors == null) return;

        for (int i = circleDeflectors.size()-1; i>=0; i--) {
            circleDeflectors.get(i).display(this);

            if(circleDeflectors.get(i).dead){
                circleDeflectors.remove(i);
            }
        }
    }

    boolean justAddedEmitter = false;
    boolean justAddedSquareDeflector = false;
    boolean justAddedCircleDeflector = false;
    public void play(){
        if(this.player != null){
            this.player.display(this);
        }

        drawBullets();
        drawEmitters();
        
        drawSquareDeflectors();
        drawCircleDeflectors();

        int multiplier = 15;
        if(frames % Math.floor(bulletRate * 10 * multiplier) == 0 && frames != 0 && !justAddedEmitter){
            addEmitter((int)(Math.random() * 800), (int)(Math.random() * 800));

            justAddedEmitter = true;
        } else if(justAddedEmitter && frames % Math.floor(bulletRate * 10 * multiplier) != 0){
            justAddedEmitter = false;
        }

        if(frames % (92 * multiplier) == 0 && frames != 0 && !justAddedSquareDeflector){
            addSquareDeflector(
                (int)(Math.random() * 800), (int)(Math.random() * 800), 
                200 + (int)(Math.random() * 100), 200 + (int)(Math.random() * 100)
            );

            justAddedSquareDeflector = true;
        } else if(justAddedSquareDeflector && frames % (92 * multiplier) != 0){
            justAddedSquareDeflector = false;
        }

        if(frames % (73 * multiplier) == 0 && frames != 0 && !justAddedCircleDeflector){
            addCircleDeflector(
                (int)(Math.random() * 800), (int)(Math.random() * 800), 
                100 + (int)(Math.random() * 100)
            );

            justAddedCircleDeflector = true;
        } else if(justAddedCircleDeflector && frames % (73 * multiplier) != 0){
            justAddedCircleDeflector = false;
        }

        // System.out.println(dbullets.size());

        fontSize(30);
        fill(70);
        text("" + score, 20, 40);

        bulletRate -= 0.02;

        if(bulletRate <= 10) {
            bulletRate = 10;
        }
    }

    public Button back = new Button("Back to menu", -5, 390, 500, 40, new Function(){
        public void function(){
            scene = "home";
        }
    });
    public void how(){
        fontSize(50);
        fill(70);

        text("How to play", 20, 300);

        fontSize(20);
        text("Use arrow keys to avoid the little black squares coming from the circle thingies", 20, 350);

        back.display(this);
    }

    public void reset(){
        bullets.clear();

        squareDeflectors.clear();
        circleDeflectors.clear();

        emitters.clear();
        emitters.add(new Emitter(400, 400));

        frames = 1;

        bulletRate = 60;

        player.x = 50;
        player.y = 50;

        player.xv = 0;
        player.yv = 0;

        score = 0;
    }

    public Button play = new Button("Play", -5, 350, 500, 40, new Function(){
        public void function(){
            reset();

            // addCircleDeflector(30, 30, 200);

            scene = "play";
        }
    });
    public Button how = new Button("How to play", -5, 410, 450, 40, new Function(){
        public void function(){
            scene = "how";
        }
    });
    public void home(){
        if(this.play == null){ return; }

        fontSize(50);
        fill(70);

        text("Some Game", 20, 300);

        play.display(this);
        how.display(this);
    }

    public Button retry = new Button("Try again?", -5, 350, 500, 40, new Function(){
        public void function(){
            reset();

            scene = "play";
        }
    });
    public Button home = new Button("Back to menu", -5, 410, 450, 40, new Function(){
        public void function(){
            scene = "home";
        }
    });
    public void dead(){
        fontSize(50);
        fill(70);

        text("You Ded", 20, 300);

        fontSize(20);
        text("Your score: " + score, 20, 330);

        retry.display(this);
        home.display(this);
    }

    public void draw(){
        strokeWeight(0);
        fill(250);
        rect(0, -1, frameWidth, frameHeight);

        if(this.scene == null) return;

        switch(this.scene){
            case "home":
                home();
                break;
            case "play":
                play();
                break;
            case "dead":
                dead();
                break;
            case "how":
                how();
                break;
        }

        frames++;
    }

    public static void main(String[] args){
        App window = new App();

        // double plz = Math.atan2();
    }
}
