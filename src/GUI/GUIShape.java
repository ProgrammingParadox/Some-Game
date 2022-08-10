package GUI;

import java.awt.*; // Import everything from the awt package
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GUIShape {
    public String type;
    public Object[] args;

    public int[] fill = new int[4];
    public int[] stroke = new int[4];
    public int strokeWeight = 1;

    public String fontName = "Trebuchet MS";
    public int fontSize = 12;

    public ArrayList<Pair<String, double[]>> transformations;

    public GUIShape(String type, int[] fill, int[] stroke, int strokeWeight, Object[] args){
        this.type = type;
        this.args = args;
    
        this.fill = fill;
        this.stroke = stroke;
    
        this.strokeWeight = strokeWeight;
    }
    public GUIShape(String type, int[] fill, int[] stroke, int strokeWeight, ArrayList<Pair<String, double[]>> transformations, Object[] args){
        this.type = type;
        this.args = args;
    
        this.fill = fill;
        this.stroke = stroke;
    
        this.strokeWeight = strokeWeight;

        this.transformations = transformations;
    }
    public GUIShape(String type, int[] fill, String fontName, int fontSize, Object[] args){
        this.type = type;
        this.args = args;

        this.fill = fill;

        this.fontName = fontName;
        this.fontSize = fontSize;
    }
    public GUIShape(String type, int[] fill, String fontName, int fontSize, ArrayList<Pair<String, double[]>> transformations, Object[] args){
            this.type = type;
            this.args = args;

            this.fill = fill;

            this.fontName = fontName;
            this.fontSize = fontSize;

            this.transformations = transformations;
    }
    
    public void handleTransformation(Graphics2D g, Pair<String, double[]> t){
        switch(t.k){
            case "translate":
                g.translate(t.v[0], t.v[1]);
                break;
            case "rotate":
                g.rotate(t.v[0]);
                break;
            case "scale":
                g.scale(t.v[0], t.v[1]);
                break;
            case "shear":
                g.shear(t.v[0], t.v[1]);
                break;
        }
    }
    
    public void display(Graphics2D g) {
        AffineTransform nothing = new AffineTransform();
        nothing.setToScale(2.0, 2.0);

        g.setTransform(nothing);

        if(this.transformations != null){
            for(int i = 0; i<this.transformations.size(); i++){
                handleTransformation(g, this.transformations.get(i));
            }
        }

        switch (this.type) {
            case "rect":
                if(this.args.length == 5){
                    g.setPaint(new Color(this.fill[0], this.fill[1], this.fill[2], this.fill[3]));
                    g.fillRoundRect(
                        (int) this.args[0],
                        (int) this.args[1],
                        (int) this.args[2],
                        (int) this.args[3],
                        (int) this.args[4],
                        (int) this.args[4]
                    );

                    if (this.strokeWeight <= 0) {
                        return;
                    }

                    g.setPaint(new Color(this.stroke[0], this.stroke[1], this.stroke[2], this.stroke[3]));
                    g.setStroke(new BasicStroke(strokeWeight));
                    g.drawRoundRect(
                        (int) this.args[0],
                        (int) this.args[1],
                        (int) this.args[2],
                        (int) this.args[3],
                        (int) this.args[4],
                        (int) this.args[4]
                    );

                    return;
                }

                g.setPaint(new Color(this.fill[0], this.fill[1], this.fill[2], this.fill[3]));
                g.fillRect(
                    (int) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2],
                    (int) this.args[3]
                );

                if (this.strokeWeight <= 0) {
                    return;
                }

                g.setPaint(new Color(this.stroke[0], this.stroke[1], this.stroke[2], this.stroke[3]));
                g.setStroke(new BasicStroke(strokeWeight));
                g.drawRect(
                    (int) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2],
                    (int) this.args[3]
                );
                break;
            case "ellipse":
                g.setPaint(new Color(this.fill[0], this.fill[1], this.fill[2], this.fill[3]));

                g.fillOval(
                    (int) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2],
                    (int) this.args[3]
                );

                g.setPaint(new Color(this.stroke[0], this.stroke[1], this.stroke[2], this.stroke[3]));
                g.setStroke(new BasicStroke(strokeWeight));
                g.drawOval(
                    (int) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2],
                    (int) this.args[3]
                );
                break;
            case "polygon":
                g.setPaint(new Color(this.fill[0], this.fill[1], this.fill[2], this.fill[3]));

                g.fillPolygon(
                    (int[]) this.args[0],
                    (int[]) this.args[1],
                    ((int[]) this.args[0]).length
                );

                g.setPaint(new Color(this.stroke[0], this.stroke[1], this.stroke[2], this.stroke[3]));
                g.setStroke(new BasicStroke(strokeWeight));
                g.drawPolygon(
                    (int[]) this.args[0],
                    (int[]) this.args[1],
                    ((int[]) this.args[0]).length
                );
                break;
            case "line":
                g.setPaint(new Color(this.stroke[0], this.stroke[1], this.stroke[2], this.stroke[3]));

                g.drawLine(
                    (int) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2],
                    (int) this.args[3]
                );
                break;
            case "text":
                g.setFont(new Font(this.fontName, Font.PLAIN, this.fontSize));

                g.setPaint(new Color(this.fill[0], this.fill[1], this.fill[2], this.fill[3]));

                g.drawString(
                    (String) this.args[0],
                    (int) this.args[1],
                    (int) this.args[2]
                );
                break;
        }

        g.setTransform(nothing);
    }

    public static void main(String[] args){
        System.out.println("Hejjo");
    }
}
