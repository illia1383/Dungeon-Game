/**
 * 2023-03-21
 * Illia Lotfalian
 */


import java.io.IOException;

public class FindShortestPath {
    /**
     * Using a try catch statement
     * @param args
     */
    public static void main(String[] args ) {
        //first check if the file can be opened
        try {
            if (args.length < 1) {
                throw new Exception("No input file specified");
            }

            String dungeonFileName = args[0];
            Dungeon dungeon = new Dungeon(dungeonFileName);
            DLPriorityQueue<Hexagon> prioList = new DLPriorityQueue<Hexagon>(); //creating an empty prio queue


            //Getting the starting chamber
            Hexagon startingChamber = dungeon.getStart();
            //Adding the starting the chamber to the prio list with a prio of 0
            prioList.add(startingChamber, 0);
            //Marking the StartingChamber as enqueued
            startingChamber.markEnqueued();


            //Making a while loop
            // I need to make a current variable to go through the different paths
            int D = -1;
            boolean exit = false;

            while (!prioList.isEmpty() && !exit) {
                Hexagon curr = prioList.removeMin();
                curr.markDequeued();
                if (curr.isExit()) {
                    exit = true; //this exits the while loop
                    D = curr.getDistanceToStart() +1;

                } else if (neighbourIsDragon(curr)) {
                    //System.out.println("dragon");
                    continue; //To make it go to the next chamber

                } else {
                    //System.out.println("GOT HERE !!!! NOT DRAGON");
                    for (int i = 0; i < 6; i++) {
                        Hexagon neighbour = curr.getNeighbour(i);
                        if (neighbour != null) {
                            if (!neighbour.isWall() && !neighbour.isMarkedDequeued()) {
                                //System.out.println("GOT HERE HERE");
                                D = 1 + curr.getDistanceToStart(); // Current to initial chamber
                                System.out.println(D);
                                if (neighbour.getDistanceToStart() > D) {
                                    //System.out.println("GOT HERE  ITS GREATEDERERRERE#R GREATER GREATER");

                                    neighbour.setDistanceToStart(D);
                                    neighbour.setPredecessor(curr);
                                }
                                if (neighbour.isMarkedEnqueued()) {
                                    prioList.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));

                                } else {
                                    prioList.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
                                    neighbour.markEnqueued();
                                }


                            }

                        }

                    }


                }


            }
            if (exit == true) {
                System.out.println("Path of length " + D + " ");
            } else {
                System.out.println("No path found");
            }

        } catch (IOException e) {
            System.out.println("File not found");
        } catch (EmptyPriorityQueueException e) {
            System.out.println("Empty priority Queue" + e.getMessage());
        } catch (InvalidElementException e) {
            System.out.println("Invalid element" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unkown execption caught" + e.getMessage());
        }
    }

    private static boolean neighbourIsDragon (Hexagon curr) {//helper method to put in the if statement
        if(curr.isDragon()){
            return true;
        }
        //Now have to use for loop to get neighbours
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = curr.getNeighbour(i);
            if (neighbour != null && neighbour.isDragon()) {
                    return true;


            }
        }

        return false;
    }
}