package linkedin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionVariableDemo {

    private static class HungryPersonNoCV extends Thread {

        private int personID;
        private static Lock slowCookerLid = new ReentrantLock();
        private static int servings = 11;

        public HungryPersonNoCV(int personID) {
            this.personID = personID;
        }

        @Override
        public void run() {
            while (servings > 0) {
                slowCookerLid.lock();
                try {
                    if ((personID == servings % 2) && servings > 0) { // check if it's your turn
                        servings--; // it's your turn - take some soup!
                        System.out.format("Person %d took some soup! Servings left: %d\n", personID,
                                          servings);
                    } else { // not your turn - put the lid back
                        System.out
                                .format("Person %d checked... then put the lid back.\n", personID);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    slowCookerLid.unlock();
                }
            }
        }
    }

    private static class HungryPerson extends Thread {

        private int personID;
        private static Lock slowCookerLid = new ReentrantLock();
        private static int servings = 11;
        private static Condition soupTaken = slowCookerLid.newCondition(); // Condition variable

        public HungryPerson(int personID) {
            this.personID = personID;
        }

        @Override
        public void run() {
            while (servings > 0) {
                slowCookerLid.lock();
                try {
                    while ((personID != servings % 2) &&
                           servings > 0) { // check if it's not your turn
                        System.out.format("Person %d checked... then put the lid back.\n",
                                          personID);
                        soupTaken.await();
                    }
                    if (servings > 0) {
                        servings--; // it's your turn - take some soup!
                        System.out.format("Person %d took some soup! Servings left: %d\n",
                                          personID, servings);
                        soupTaken.signalAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    slowCookerLid.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            //new HungryPersonNoCV(i).start();
            new HungryPerson(i).start();
        }
    }
}
