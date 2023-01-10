public class Main {
    public static void main(String[] args) {
        // task 3 starts. 
        Task3 task3 = new Task3();

        // task 1 needs task 3 to complete.
        Task1 task1 = new Task1(task3);

        // task 2 needs task 1 and task 3 to complete. 
        Task2 task2 = new Task2(task1, task3);

        // task 5 needs task 2 and task 3 to complete.
        Task5 task5 = new Task5(task2, task3);

        // task 4 needs task 5 and task 3 to complete.
        Task4 task4 = new Task4(task3, task5);
        
        // task 6 needs task 3 and task 4 to complete.
        Task6 task6 = new Task6(task3, task4);

        // Set dependencies
        task3.start();
        task1.start();
        task2.start();
        task5.start();
        task4.start();
        task6.start();
    }
}

class Task1 extends Thread {
    private Thread dependentThread;

    public Task1(Thread dependentThread) {
        this.dependentThread = dependentThread;
        setName("Task1");
    }

    public void run() {
        try {
            dependentThread.join();
            System.out.println(getName() + ": Dependent task " + dependentThread.getName() + " completed");
            System.out.println(getName() + ": Running " + this.getClass().getName());
        } catch (InterruptedException e) {
        }
        
    }
}

class Task2 extends Thread {
    private Thread dependentThread1;
    private Thread dependentThread2;

    public Task2(Thread dependentThread1, Thread dependentThread2) {
        this.dependentThread1 = dependentThread1;
        this.dependentThread2 = dependentThread2;
        setName("Task2");
    }

    public void run() {
        try {
            dependentThread1.join();
            System.out.println(getName() + ": Dependent task " + dependentThread1.getName() + " completed");
            dependentThread2.join();
            System.out.println(getName() + ": Dependent task " + dependentThread2.getName() + " completed");
            System.out.println(getName() + ": Running " + this.getClass().getName());
        } catch (InterruptedException e) {
        }
    }
}

class Task3 extends Thread {
    public Task3() {
        setName("Task3");
    }

    public void run() {
        System.out.println("Running " + this.getClass().getName());
    }
}

class Task4 extends Thread {
    private Thread dependentThread1;
    private Thread dependentThread2;

    public Task4(Thread dependentThread1, Thread dependentThread2) {
        this.dependentThread1 = dependentThread1;
        this.dependentThread2 = dependentThread2;
        setName("Task4");
    }

    public void run() {
        try {
            dependentThread1.join();
            System.out.println(getName() + ": Dependent task " + dependentThread1.getName() + " completed");
            dependentThread2.join();
            System.out.println(getName() + ": Dependent task " + dependentThread2.getName() + " completed");
            System.out.println(getName() + ": Running " + this.getClass().getName());
        } catch (InterruptedException e) {
        }
    }
}

class Task5 extends Thread {
    private Thread dependentThread1;
    private Thread dependentThread2;

    public Task5(Thread dependentThread1, Thread dependentThread2) {
        this.dependentThread1 = dependentThread1;
        this.dependentThread2 = dependentThread2;
        setName("Task5");
    }

    public void run() {
        try {
            dependentThread1.join();
            System.out.println(getName() + ": Dependent task " + dependentThread1.getName() + " completed");
            dependentThread2.join();
            System.out.println(getName() + ": Dependent task " + dependentThread2.getName() + " completed");
            System.out.println(getName() + ": Running " + this.getClass().getName());
        } catch (InterruptedException e) {
        }
    }
}

class Task6 extends Thread {
    private Thread dependentThread1;
    private Thread dependentThread2;

    public Task6(Thread dependentThread1, Thread dependentThread2) {
        this.dependentThread1 = dependentThread1;
        this.dependentThread2 = dependentThread2;
        setName("Task6");
    }

    public void run() {
        try {
            dependentThread1.join();
            System.out.println(getName() + ": Dependent task " + dependentThread1.getName() + " completed");
            dependentThread2.join();
            System.out.println(getName() + ": Dependent task " + dependentThread2.getName() + " completed");
            System.out.println(getName() + ": Running " + this.getClass().getName());
        } catch (InterruptedException e) {
        }
    }
}
