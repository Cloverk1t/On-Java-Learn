package ml.cloverkit.concurrent.atomic;

import ml.cloverkit.concurrent.HasID;
import ml.cloverkit.concurrent.IDChecker;

final class SyncFactory implements HasID {
    private final int id;

    private SyncFactory(SharedArg sa) {
        id = sa.get();
    }

    public static synchronized SyncFactory factory(SharedArg sa) {
        return new SyncFactory(sa);
    }

    @Override
    public int getID() {
        return id;
    }
}

public class SynchronizedFactory {
    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        IDChecker.test(() -> SyncFactory.factory(unsafe));
    }
}
