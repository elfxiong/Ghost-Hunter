package edu.virginia.cs2110.notUsed;

class myRunnable implements Runnable {
    private Object mPauseLock;
    private boolean mPaused;
    private boolean mFinished;

    public myRunnable() {
        mPauseLock = new Object();
        mPaused = false;
        mFinished = false;
    }

    public void run() {
        while (!mFinished) {
            // Do stuff.

            synchronized (mPauseLock) {
                while (mPaused) {
                    try {
                        mPauseLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    /**
     * Call this on pause.
     */
    public void onPause() {
        synchronized (mPauseLock) {
            mPaused = true;
        }
    }

    /**
     * Call this on resume.
     */
    public void onResume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
//            mPauseLock.
        }
    }

}