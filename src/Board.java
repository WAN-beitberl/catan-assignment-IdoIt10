import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.geom.*;


public class Board extends JPanel {
    private static final int HEX_RADIUS = 70;
    private static final int HEX_SIDE = (int) (HEX_RADIUS / 1 * Math.sqrt(0.547));

    //private static final int HEX_HEIGHT = (int) (HEX_RADIUS * 1.5);
    private static final int NUMBER_RADIUS = (int) (HEX_RADIUS * 0.4);

    private Terrain[][] terrain = null;
    private Resource[][] resource;
    private int[][] number;

    Random rand = new Random();

    public Board() {
        terrain = new Terrain[][]{
                        { null, null, null },
                     { null, null, null, null },
                  { null, null, null, null, null },
                     { null, null, null, null },
                        { null, null, null }
        };
        int row = rand.nextInt(terrain.length);
        int col = rand.nextInt(terrain[row].length);
        terrain[row][col] = Terrain.LAND;
        int terrainCounter[] = {5, 3, 3, 4, 4};
        makeRand(terrainCounter, "terrain", 5);

        number = new int[][]{
                    { -1, -1, -1 },
                  { -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1 },
                  { -1, -1, -1, -1},
                    { -1, -1, -1}
        };
        number[row][col] = -2;
        int numCounter[] = { 0, 0, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1 };
        makeRand(numCounter, "number", 13);
    }

    protected void makeRand(int array[], String name, int numRand){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (!isClean(array)){
            int i = rand.nextInt(numRand);
            if (array[i] > 0){
                array[i]--;
                nums.add(i);
            }
        }

        if (name == "number"){
            int x = 0;
            for (int i = 0; i < number.length; i++){
                for (int j = 0; j < number[i].length; j++){
                    if (number[i][j] != -2) {
                        number[i][j] = (int)(nums.get(x++));
                    }
                }
            }
        }

        if (name == "terrain"){
            int x = 0;
            for (int i = 0; i < terrain.length; i++){
                for (int j = 0; j < terrain[i].length; j++){
                    if (terrain[i][j] == null){
                        int num = (int) (nums.get(x++));
                        terrain[i][j] = getNameFromEnumTerrain(num);
                    }
                }
            }
        }
    }


    protected Terrain getNameFromEnumTerrain(int num){
        switch (num){
            case 0:
                return Terrain.FIELDS;

            case 1:
                return Terrain.HILLS;

            case 2:
                return Terrain.MOUNTAINS;

            case 3:
                return Terrain.PASTURE;

            case 4:
                return Terrain.FOREST;

            case 5:
                return Terrain.LAND;
        }
        return null;
    }
    protected boolean isClean(int[] array){
        for (int num : array){
            if (num != 0)
                return false;
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < terrain.length; row++) {
            for (int col = 0; col < terrain[row].length; col++) {
                paintHexTile(g, row, col);
            }
        }
    }
    private void paintHexTile(Graphics g, int row, int col) {
        int x = col * (HEX_RADIUS + HEX_SIDE) + HEX_RADIUS;
        int y = row * (HEX_RADIUS + (HEX_RADIUS / 2)) + HEX_RADIUS;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int numRows = terrain.length;
        int numCols = terrain[0].length;
        int mid = (numCols + numRows) / 2;

        int[] xCoords = new int[6];
        int[] yCoords = new int[6];

        if (row == 0 || row == 4) {
            x += mid * 105;
        }
        if (row == 3 || row == 1) {
            x += mid * 90;
        }
        if (row == 2) {
            x += mid * 75;
        }

        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3.0 * i;
            xCoords[i] = (int) (x + HEX_RADIUS * Math.cos(angle + (Math.PI / 6.0)));
            yCoords[i] = (int) (y + HEX_RADIUS * Math.sin(angle + (Math.PI / 6.0)));
        }

        g.setColor(Color.WHITE);
        g.fillPolygon(xCoords, yCoords, 6);
        g.setColor(Color.BLACK);
        g.drawPolygon(xCoords, yCoords, 6);

        // Draw number token
        int num = number[row][col];

        Terrain ter = terrain[row][col];

        if (ter != null) {
            Image img = null;
            switch (ter) {
                case MOUNTAINS:
                    g.setColor(Color.gray);
                    break;
                case FIELDS:
                    g.setColor(Color.yellow);
                    break;
                case HILLS:
                    g.setColor(Color.red);
                    break;
                case PASTURE:
                    g.setColor(Color.green);
                    break;
                case FOREST:
                    g.setColor(new Color(0x0E9B0E));
                    break;
                case LAND:
                    g.setColor(Color.white);
                    break;
            }

            if (num != -2) {
                g.fillPolygon(xCoords, yCoords, 6);
                g.setColor(Color.BLACK);
                g.drawOval(x - NUMBER_RADIUS, y - NUMBER_RADIUS, NUMBER_RADIUS * 2, NUMBER_RADIUS * 2);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString(Integer.toString(num), x - 8, y + 6);
            }
        }

        setLayout(null);
        int[] xCoords2 = new int[6];
        int[] yCoords2 = new int[6];
        for (int i = 0; i < 6; i++) {
            xCoords2[i] = (int) (HEX_RADIUS * Math.cos(Math.PI / 3.0 * i + Math.PI / 6.0));
            yCoords2[i] = (int) (HEX_RADIUS * Math.sin(Math.PI / 3.0 * i + Math.PI / 6.0));
        }

        int numSides = 6;
        int buttonWidth = HEX_RADIUS / 6;
        int buttonHeight = (int) Math.sqrt(Math.pow(HEX_RADIUS, 2) - Math.pow(buttonWidth / 2, 2));
        int offset = (HEX_RADIUS - buttonHeight) / 2;
        for (int i = 0; i < numSides; i++) {
            int startX = xCoords[i];
            int startY = yCoords[i];
            int endX = xCoords[(i + 1) % 6];
            int endY = yCoords[(i + 1) % 6];
            centerX = (startX + endX) / 2;
            centerY = (startY + endY) / 2;
            int buttonX = centerX - buttonWidth / 2;
            int buttonY = centerY - buttonHeight / 2 + offset;

            RotateButton intersectionButton = new RotateButton("", 0);

            if(i == 0 || i == 3){
                intersectionButton.UpdateRotateButton(1);
            }
            if(i == 1 || i == 4){
                intersectionButton.UpdateRotateButton(5.2);
            }

            intersectionButton.setOpaque(false);
            intersectionButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            intersectionButton.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            add(intersectionButton);

            RotateButton finalIntersectionButton = intersectionButton;
            intersectionButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    finalIntersectionButton.setBackground(Color.white);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    finalIntersectionButton.setBackground(Color.white);
                }
            });
            buttonHeight = (int) Math.sqrt(Math.pow(HEX_RADIUS, 2) - Math.pow(buttonWidth / 2, 2));
        }
    }

}