package com.gl.training.fullstack.java.ques1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SkyscraperDriverMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of floors to be constructed. ");
        int noOfFloors = scanner.nextInt();
        List<Integer> floorList = new ArrayList<>(noOfFloors);

        for (int i = 0; i < noOfFloors; i++) {
            System.out.println("Please enter the floor size given on day :" + (i + 1));
            floorList.add(scanner.nextInt());
        }
        // create a stack which holds floor size in ascending order.It means floor with large size will be on top
        Stack<Integer> sortedFloorSizeStack = new Stack<>();
        List<Integer> floorList1 = new ArrayList<>(floorList);
        floorList1.sort(Integer::compareTo);
        sortedFloorSizeStack.addAll(floorList1);
        printNumberOfFloorsConstructed(floorList, sortedFloorSizeStack);


    }

    private static void printNumberOfFloorsConstructed(List<Integer> floorList, Stack<Integer> floorsStack) {

        int nofOfDaysConstructed = 1;
        // checking if stack is empty , if not continue else all floors are constructed
        while (!floorsStack.isEmpty()) {
            //  pop the element from top which is largest
            int element = floorsStack.pop();
            // find the day on which floor with the largest size received.
            int dayOfFloorReceieved = findDayOfElement(element, floorList);
            // printing the blank values for days before receiving the largest number
            while (dayOfFloorReceieved > nofOfDaysConstructed) {
                System.out.println("Day :" + nofOfDaysConstructed);
                System.out.println("");
                nofOfDaysConstructed++;
            }
            //printing the elements from day on received floor with the largest size and subsequent ones
            System.out.println("Day :" + nofOfDaysConstructed);
            System.out.print(element + " ");
            nofOfDaysConstructed++;
            boolean isAvailable = false;
            do {
                isAvailable = false;
                Integer secondLargestFloor = floorsStack.peek();
                if (floorList.subList(0, dayOfFloorReceieved - 1).contains(secondLargestFloor)) {
                    System.out.print(secondLargestFloor + " ");
                    floorsStack.pop();
                    isAvailable = true;
                }
            } while (isAvailable & !floorsStack.isEmpty());
            System.out.println("");


        }

    }

    private static int findDayOfElement(int element, List<Integer> floorList) {
        return floorList.indexOf(element) + 1;
    }


}

