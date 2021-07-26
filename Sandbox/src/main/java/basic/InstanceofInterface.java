package basic;

interface Vehicle {
    void drive();
}

abstract class Mammal {}

abstract class DrivableMammal extends Mammal implements Vehicle {
    public void drive() {
        System.out.println(getClass().getName() + ": It's a drivable!");
    }
}

class Bear extends Mammal {}

class Elephant extends DrivableMammal {}

class Horse extends DrivableMammal {}

class Lion extends Mammal {}

class InstanceofInterface {
    public static final int NUMMAMMALS = 4;

    public static void main(String[] args) {
        // 哺乳動物の配列を作成する
        Mammal mammals[] = new Mammal[NUMMAMMALS];

        // オブジェクトを作成する
        mammals[0] = new Bear();
        mammals[1] = new Elephant();
        mammals[2] = new Horse();
        mammals[3] = new Lion();

        // instanceof演算子を利用する
        for (int i = 0; i < NUMMAMMALS; i++) {
            if (mammals[i] instanceof Vehicle) {
                DrivableMammal v = (DrivableMammal) mammals[i];
                v.drive();
                // mammals[i].drive(); // Mammalには、drive()が定義されていないのでエラー
            }
        }
    }
}
