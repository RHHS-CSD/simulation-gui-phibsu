/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automatastarter;

import java.awt.Graphics;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author kittypublic class ArraySimulation {
 *
 */
public class ArraySimulation {

    //variables
    final static int ROWS = 20;
    final static int COLUMNS = 20;
    final static int INITIAL_PREY_COUNT = 100;
    final static int INITIAL_PREDATOR_COUNT = 10;

    final static int PREDATOR_VISION = 5;
    final static int PREDATOR_STARVE = 10;
    final static int PREDATOR_REPRODUCE = 5;
    //reproduction percent chance (under 100)
    final static int PREDATOR_REPRODUCE_CHANCE = 5;
    final static int PREY_REPRODUCE_CHANCE = 10;

    int[][] grid;
    ArrayList prey;
    ArrayList predators;

    Random rng = new Random();

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//
//        Scanner input = new Scanner(System.in);
//        String word = "";
//        boolean inPlay = true;
//
//        //create and fill grid with initial positions
//        ArraySimulation game = new ArraySimulation();
//        game.fillGrid();
//
//        //loop the simulation each time the user presses enter
//        while (inPlay) {
//            System.out.println(game.drawGrid());
//            word = input.nextLine();
//            game.step();
//            //exit when the user types 'q'
//            if (word.equalsIgnoreCase("Q")) {
//                inPlay = false;
//            }
//        }
//    }
    public ArraySimulation() {
        grid = new int[ROWS][COLUMNS]; //0:empty // 1:prey // 2:predator
        predators = new ArrayList(0); //y,x, hunger
        prey = new ArrayList(0); //y,x
    }

    protected void randomizeGrid() {
        int row;
        int column;
        //generate prey locations
        for (int i = 0; i < INITIAL_PREY_COUNT; i++) {
            row = rng.nextInt(grid.length - 1);
            column = rng.nextInt(grid[0].length - 1);

            //if something already occupies the tile, regenerate the location
            if (grid[row][column] != 0) {
                i -= 1;
            } else {
                //otherwise, add the location to the grid as well as prey list
                grid[row][column] = 1;
            }
        }
        //generate predator locations
        for (int i = 0; i < INITIAL_PREDATOR_COUNT; i++) {
            row = rng.nextInt(grid.length - 1);
            column = rng.nextInt(grid[0].length - 1);

            //if something already occupies the tile, regenerate the location
            if (grid[row][column] != 0) {
                i -= 1;
            } else {
                //otherwise, add the location to the grid as well as predator list
                grid[row][column] = 2;
            }
        }
    }

    protected void fillGrid() {
        //loop through each grid square

        for (int r = 0; r < grid[0].length; r++) {
            for (int c = 0; c < grid.length; c++) {
                //if a grid array has an animal, add it to their repsective array
                switch (grid[r][c]) {
                    case 1:
                        prey.add(new int[]{r, c});
                        break;
                    case 2:
                        predators.add(new int[]{r, c, 0});
                }
            }
        }
    }

    protected void editTile(int row, int col, int type) {
        //toggle the grid type if the selection is the same as the existing one
        if (grid[row][col] == type) {
            grid[row][col] = 0;
        } else {
            grid[row][col] = type;
        }
    }

    protected void reset() {
        //remove everything from the grid
        for (int c = 0; c < grid.length; c++) {
            for (int r = 0; r < grid[0].length; r++) {
                grid[c][r] = 0;
            }
        }
        prey.clear();
        predators.clear();
    }

    protected String drawGrid() {
        String output = "";
        for (int[] column : grid) {
            for (int tile : column) {
                switch (tile) {
                    case 1:
                        output += "@ ";
                        break;
                    case 2:
                        output += "P ";
                        break;
                    default:
                        output += ". ";
                        break;
                }
            }
            output += "\n";
        }
        return output;
    }

    protected void step() {
        //predators always move first

        ArrayList newPredators = cloneList(predators);
        for (int i = 0; i < newPredators.size(); i++) {
            movePredator((int[]) newPredators.get(i));
        }
        ArrayList newPrey = cloneList(prey);
        for (int i = 0; i < newPrey.size(); i++) {
            movePrey((int[]) newPrey.get(i));
        }

    }

    //every action for predators
    protected void movePredator(int[] predator) {
        int col = predator[1];
        int row = predator[0];
        int hunger = predator[2];

        //remove dead
        if (hunger == PREDATOR_STARVE) {
            predators.remove(predator);
            grid[predator[0]][predator[1]] = 0;
            return;
        } else if (hunger < PREDATOR_REPRODUCE) {
            //attempt to reproduce if predator has eaten recently
            //if it attempts to reproduce, it does not move
            boolean reproduce = rng.nextInt(100 / PREDATOR_REPRODUCE_CHANCE) == 0;
            if (reproduce) {
                reproduce(predator, false);
                return;
            }
        }
        //add hunger
        predator[2] += 1;

        /*for predators, find the nearest prey to move toward
        their vision is in a square around them */
        //coordinates of closest prey
        int[] closest = null;
        int distance = ROWS + COLUMNS;

        //loop through visible tiles to determine the closest prey
        //this order will prioritize prey closest to the bottom right if there are any ties
        //create a start coordinate for where serach begins based on predator's vision
        //when row value goes above grid, make end position at the bottom 
        int[] start = {row - PREDATOR_VISION < 0 ? row + grid[0].length - PREDATOR_VISION : row - PREDATOR_VISION,
            //when column value goes to left of grid, make end position on the right 
            col - PREDATOR_VISION < 0 ? col + grid[1].length - PREDATOR_VISION : col - PREDATOR_VISION};

        //create a target cooredinate (where the search for closes prey ends
        //always bottom right relative to predator) for columns and rows
        //when row value goes below grid, make end position at the top 
        int[] target = {row + PREDATOR_VISION > grid[0].length ? row - grid[0].length + PREDATOR_VISION : row + PREDATOR_VISION,
            //when column value goes to right of grid, make end position on the left 
            col + PREDATOR_VISION > grid[1].length ? col - grid[1].length + PREDATOR_VISION : col + PREDATOR_VISION};

        //variables representing relative distance from selected predator 
        int relC = -PREDATOR_VISION;
        int relR = -PREDATOR_VISION;

        //loop through possible tiles that are in the predator's vision
        for (int c = start[0]; c != target[0]; c++) {
            //reset c to not go out of bounds
            if (c >= grid[0].length) {
                c = 0;
            }
            for (int r = start[1]; r != target[1]; r++) {
                //reset r
                if (r >= grid.length) {
                    r = 0;
                }

                //find the closest prey by checking the distance (counts number of moves to reach the tile) between the predator and every prey in vicinity
                if (grid[c][r] == 1 && Math.abs(relR) + Math.abs(relC) < distance) {
                    closest = new int[]{c, r};
                    distance = Math.abs(relR) + Math.abs(relC);
                }
                relR++;
            }
            relC++;
            relR = -PREDATOR_VISION;
        }

        //if prey nearby, move towards it
        if (closest != null) {
            relR = closest[0] - predator[0];
            relC = closest[1] - predator[1];
            if (relR < -PREDATOR_VISION) {
                relR += ROWS;
            } else if (relR > PREDATOR_VISION) {
                relR -= ROWS;
            }
            if (relC < -PREDATOR_VISION) {
                relC += COLUMNS;
            } else if (relC > PREDATOR_VISION) {
                relC -= COLUMNS;
            }

            //prioritize moving in the direction where predator is farther
            //vertical first
            if (Math.abs(relR) > Math.abs(relC)) {
                int direction = relR < 0 ? 1 : 2;
                int[] temp = nextTile(direction, predator);

                //move if the next tile is not blocked by another predator
                switch (grid[temp[0]][temp[1]]) {
                    case 0:
                        moveTo(temp, predator, false);
                        break;

                    //if next tile is prey, remove the prey
                    case 1:
                        eat(predator, temp);
                        break;

                    //if blocked, attempt the other direction
                    default:
                        direction = relC < 0 ? 3 : 4;
                        temp = nextTile(direction, predator);

                        //move if the next tile is not blocked by another predator
                        switch (grid[temp[0]][temp[1]]) {
                            case 0:
                                moveTo(temp, predator, false);
                                break;

                            //if next tile is prey, remove the prey
                            case 1:
                                eat(predator, temp);
                                break;
                        }
                        break;
                }

            } //horizontal first
            else {
                int direction = relC < 0 ? 3 : 4;
                int[] temp = nextTile(direction, predator);
                //move if the next tile is not blocked by another predator
                switch (grid[temp[0]][temp[1]]) {
                    case 0:
                        moveTo(temp, predator, false);
                        break;

                    //if next tile is prey, remove the prey
                    case 1:
                        eat(predator, temp);
                        break;

                    //if blocked, attempt the other direction
                    default:
                        direction = relR < 0 ? 1 : 2;
                        temp = nextTile(direction, predator);

                        //move if the next tile is not blocked by another predator
                        switch (grid[temp[0]][temp[1]]) {
                            case 0:
                                moveTo(temp, predator, false);
                                break;

                            //if next tile is prey, remove the prey
                            case 1:
                                eat(predator, temp);
                                break;
                        }
                        break;
                }
            }
            //if both directions moving closer to the prey is blocked, do not move
        } //if no prey in vicinity, move randomly
        //if it gets blocked, does not move
        else {

            int direction = rng.nextInt(5 + 1); // order is up, down, left, right
            int[] temp = nextTile(direction, predator);
            if (grid[temp[0]][temp[1]] == 0) {
                moveTo(temp, predator, false);

            }

        }
    }

    protected void movePrey(int[] prey1) {
        //prey die if all adjacent tiles are filled
        for (int i = 1; i <= 4; i++) {
            int[] next = nextTile(i, prey1);
            if (grid[next[0]][next[1]] != 0 && i == 4) {
                prey.remove(prey1);
                grid[prey1[0]][prey1[1]] = 0;
                return;
            }
        }

        //attempt to reproduce
        //if success, it cannot move
        boolean reproduce = rng.nextInt(100 / PREY_REPRODUCE_CHANCE) == 0;
        if (reproduce) {
            reproduce(prey1, true);
            return;
        }

        //attempt to move in a random direction
        //if failed, turn is lost
        int direction = rng.nextInt(5 + 1); // order is up, down, left, right
        int[] temp = nextTile(direction, prey1);
        if (grid[temp[0]][temp[1]] == 0) {
            moveTo(temp, prey1, true);
        }
    }

    protected void reproduce(int[] animal, boolean isPrey) {
        //spawn the child on top of the animal
        //loop through the four adjacent tiles
        for (int i = 1; i <= 4; i++) {
            int[] next = nextTile(i, animal);
            if (grid[next[0]][next[1]] == 0) {

                if (isPrey) {
                    grid[next[0]][next[1]] = 1;
                    prey.add(new int[]{next[0], next[1]});
                } else {
                    grid[next[0]][next[1]] = 2;
                    predators.add(new int[]{next[0], next[1], 0});
                }
                return;
            }
        }
    }

    protected void eat(int[] predator, int[] loc) {
        predator[2] = 0;
        //find the prey the predator ate
        for (Iterator it = prey.iterator(); it.hasNext();) {
            int[] prey1 = (int[]) it.next();
            if (prey1[0] == loc[0] && prey1[1] == loc[1]) {
                prey.remove(prey1);
                break;
            }
        }

        moveTo(loc, predator, false);
    }

//move the animal to specified location
    protected void moveTo(int[] loc, int[] animal, boolean isPrey) {
        grid[loc[0]][loc[1]] = isPrey ? 1 : 2;
        grid[animal[0]][animal[1]] = 0;
        animal[0] = loc[0];
        animal[1] = loc[1];
    }

    protected ArrayList cloneList(ArrayList list) {
        ArrayList copy = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }
        return copy;
    }

    //method to determine the coords of the tile one step in a given direction
    protected int[] nextTile(int dir, int[] loc) {
        int[] result = {loc[0], loc[1]};
        switch (dir) {
            //up
            case 1:
                if (loc[0] > 0) {
                    result[0] -= 1;
                } else {
                    result[0] = grid.length - 1;
                }
                break;
            //down
            case 2:
                if (loc[0] < grid.length - 1) {
                    result[0] += 1;
                } else {
                    result[0] = 0;
                }
                break;
            //left
            case 3:
                if (loc[1] > 0) {
                    result[1] -= 1;
                } else {
                    result[1] = grid[0].length - 1;
                }
                break;
            //right
            case 4:
                if (loc[1] < grid[0].length - 1) {
                    result[1] += 1;
                } else {
                    result[1] = 0;
                }
        }
        return result;
    }

}
