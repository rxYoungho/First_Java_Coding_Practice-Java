package hanoiTower;

public class HanoiTower {
    Stack<Integer> towerA, towerB, towerC;
       
    public HanoiTower(int n) {
        //TODO: create StackByArray<Integer> instances for towerA .. towerC
        //      push disks from n to 1 to towerA
    }
    
    public void move() {
        move(towerA.size(), towerA /*from*/, towerB /*to*/, towerC /*aux*/);
    }
    
    private void move(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> aux) {
        if(n == 1) {
            //TODO: move disk from => to
        }
        else {
            //TODO: move n-1 disks from => aux
            //      move 1 disk from => to
            //      move n-1 disks aux => to
        }
    }
    
    private void moveDisk(Stack<Integer> from, Stack<Integer> to) {
        //TODO: pop a disk from from
        //      check if the disk is smaller than the one at the top of to
        //      push the disk onto to
        //      print the movement
    }
    
    public static void main(String[] args) {
        new HanoiTower(4).move();
    }
}
