public class UnionFind {

    private int[] array;
    private int size;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        array = new int[N];
        size = N;
        for (int i = 0; i < N; i++) {
            array[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (parent(v) < 0) {
            return Math.abs(parent(v));
        } else {
            return Math.abs(array[find(v)]);
        }
    }

    /* Returns the parent node of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return array[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (size < v) {
            throw new IllegalArgumentException();
        }
        if (array[v] < 0) {
            return v;
        } else {
            array[v] = find(array[v]);
            return array[v];
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int index1 = find(v1);
        int index2 = find(v2);
        int valueInSide1 = array[index1];
        int valueInSide2 = array[index2];

        if (index1 != index2) {
            if (Math.abs(valueInSide1) <= Math.abs(valueInSide2)) {
                array[index1] = index2;
                array[index2] += valueInSide1;
            } else {
                array[index1] += valueInSide2;
                array[index2] = index1;
            }
        }
    }
}
