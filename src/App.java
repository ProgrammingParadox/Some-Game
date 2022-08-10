import java.util.ArrayList;
import GUI.*;

public class App extends GUI {
    public Player player;

    public ArrayList<Bullet> bullets = new ArrayList<Bullet>(0);

    public String scene = "home";

    public int frames = 1;

    public double bulletRate = 30;

    public int score = 0;

    public ArrayList<Emitter> emitters = new ArrayList<Emitter>(0);

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

    public void drawBullets(){
        if(bullets == null){ return; }

        for(int i = bullets.size()-1; i>=0; i--){
            bullets.get(i).display(this, player);

            if(bullets.get(i).dead){
                bullets.remove(i);
            }

            if(player.inBullet(bullets.get(i))){
                scene = "dead";
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

    public void play(){
        if(this.player != null){
            this.player.display(this);
        }

        drawBullets();
        drawEmitters();

        if(frames % Math.floor(bulletRate * 100) == 0){
            addEmitter((int)(Math.random() * 800), (int)(Math.random() * 800));
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
