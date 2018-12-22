package pl.dmcs.references;

import javax.annotation.Nullable;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

public class ReferencesDemo {
    private static final int HOW_MANY = 50_000;

    public static void main(String[] args) {
        ReferenceQueue<HeavyList> queue = new ReferenceQueue<>();
        Set<Reference<HeavyList>> references = new HashSet<>();

        allocationLoop(queue, references, 5);
        System.gc();
        int removed = removeRefs(queue, references);
        System.out.println("Final used mem " + getUsedMem() + "    Refs removed " + removed + "   left " + references.size());
    }

    private static void allocationLoop(ReferenceQueue<HeavyList> queue, Set<Reference<HeavyList>> references, int howManyTimes) {
        HeavyList head = new HeavyList(0, null);
        HeavyList oldTail = head;
        for (int i = 0; i < howManyTimes; i++) {

            HeavyList newTail = allocate(oldTail);

            HeavyList curr = oldTail.next;
            while (curr != null) {
                //Reference<HeavyList> reference = new SoftReference<>(curr, queue);
                //Reference<HeavyList> reference = new WeakReference<>(curr, queue);
                Reference<HeavyList> reference = new PhantomReference<>(curr, queue);
                references.add(reference);

                curr = curr.getNext();
            }

            deallocateHalf(head);

            int removed = removeRefs(queue, references);

            System.gc();   //uncomment this line to comparing with forced gc
            System.out.println("Used memory= " + getUsedMem() + "    References removed= " + removed + "   references left= " + references.size());

            oldTail = newTail;
        }
        head = null;
        oldTail = null;
    }

    private static String getUsedMem() {
        NumberFormat f = new DecimalFormat("###,##0.0");
        return "Used memory: " + f.format(kbToMb(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) + "MB" + "\n";
    }

    private static double kbToMb(double mem) {
        return mem / (double) (1024 * 1024);

    }

    private static int removeRefs(ReferenceQueue queue, Set<Reference<HeavyList>> references) {
        int removed = 0;
        while (true) {
            Reference r = queue.poll();
            if (r == null)
                break;
            references.remove(r);
            removed++;
        }
        return removed;
    }

    private static void deallocateHalf(HeavyList head) {
        HeavyList curr = head;

        while (curr != null) {
            curr.dropNext();
            curr = curr.getNext();
        }
    }

    private static String getMemoryInfo() {
        NumberFormat f = new DecimalFormat("###,##0.0");

        return "Free memory: " + f.format(kbToMb(Runtime.getRuntime().freeMemory())) + "MB" + "\n" +
                "Maximum memory: " + kbToMb(Runtime.getRuntime().maxMemory()) + "MB" + "\n" +
                "Total memory: " + kbToMb(Runtime.getRuntime().totalMemory()) + "MB" + "\n";
    }

    private static HeavyList allocate(HeavyList startFrom) {
        HeavyList curr = startFrom;
        for (int i = 0; i < ReferencesDemo.HOW_MANY; i++) {
            curr = new HeavyList(i, curr);
        }
        return curr;
    }

    private static class HeavyList {
        byte[] mega = new byte[1000];
        private HeavyList next = null;

        HeavyList(int number, @Nullable HeavyList prev) {
            for (int i = 0; i < mega.length; i++) {
                mega[i] = (byte) (number % 256);
            }
            if (prev != null) {
                prev.next = this;
            }
        }

        HeavyList getNext() {
            return next;
        }

        HeavyList dropNext() {
            if (next == null || next.next == null)
                return null;
            HeavyList res = next;
            next = next.next;
            return res;
        }
    }
}
