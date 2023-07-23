package T1603;

public class ParkingSystem {
    protected int bigQueue;
    protected int midQueue;
    protected int smallQueue;

    public ParkingSystem(int big, int medium, int small) {
        bigQueue = big;
        midQueue = medium;
        smallQueue = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1 && bigQueue > 0) {
            bigQueue--;
        } else if (carType == 2 && midQueue > 0) {
            midQueue--;
        } else if (carType == 3 && smallQueue > 0) {
            smallQueue--;
        } else {
            // 没有停车位了
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ParkingSystem ps = new ParkingSystem(1, 1, 0);

        System.out.println(ps.addCar(1));
        System.out.println(ps.addCar(2));
        System.out.println(ps.addCar(3));
        System.out.println(ps.addCar(1));
    }
}
