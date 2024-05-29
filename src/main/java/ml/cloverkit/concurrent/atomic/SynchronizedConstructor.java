package ml.cloverkit.concurrent.atomic;

import ml.cloverkit.concurrent.HasID;
import ml.cloverkit.concurrent.IDChecker;

class SyncConstructor implements HasID {
    private final int id;
    private static Object constructorLock = new Object();

    SyncConstructor(SharedArg sa) {
        synchronized (constructorLock) {
            id = sa.get();
        }
    }

    @Override
    public int getID() {
        return id;
    }
}

public class SynchronizedConstructor {
    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        IDChecker.test(() -> new SyncConstructor(unsafe));
    }
}
