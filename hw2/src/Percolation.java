import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private int num;
    private boolean[][] coordinate;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF secGrid;//by creating a copy of grid without bottom
    private int topPoint;
    private int secTopPoint;
    private int bottomPoint;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size should be greater than 0!");
        }
        sideLength = N;
        num = 0;
        coordinate = new boolean[N][N];
        grid = new WeightedQuickUnionUF(N * N + 2);
        secGrid = new WeightedQuickUnionUF(N * N + 1);
        topPoint = sideLength * sideLength;
        secTopPoint = sideLength * sideLength;
        bottomPoint = sideLength * sideLength + 1;
    }

    public void open(int row, int col) {
        if (row >= sideLength || col >= sideLength) {
            throw new IndexOutOfBoundsException("row or col cannot surpass the size - 1!");
        }

        coordinate[row][col] = true;
        unionTwoGrid(row, col);
        num++;
    }

    private void unionTwoGrid(int row, int col) {
        // 检查上方的点 (row - 1, col)
        if (row > 0 && isOpen(row - 1, col)) {
            grid.union(transfer(row, col), transfer(row - 1, col));
            secGrid.union(transfer(row, col), transfer(row - 1, col));
        }
        // 检查下方的点 (row + 1, col)
        if (row < sideLength - 1 && isOpen(row + 1, col)) {
            grid.union(transfer(row, col), transfer(row + 1, col));
            secGrid.union(transfer(row, col), transfer(row + 1, col));
        }
        // 检查左边的点 (row, col - 1)
        if (col > 0 && isOpen(row, col - 1)) {
            grid.union(transfer(row, col), transfer(row, col - 1));
            secGrid.union(transfer(row, col), transfer(row, col - 1));
        }
        // 检查右边的点 (row, col + 1)
        if (col < sideLength - 1 && isOpen(row, col + 1)) {
            grid.union(transfer(row, col), transfer(row, col - 1));
            secGrid.union(transfer(row, col), transfer(row, col - 1));
        }
        // connect to topPoint or bottomPoint
        if (row == 0 && isOpen(row, col)) {
            grid.union(transfer(row, col), topPoint);
            secGrid.union(transfer(row, col), secTopPoint);
        }
        if (row == sideLength - 1 && isOpen(row, col)) {
            grid.union(transfer(row, col), bottomPoint);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row >= sideLength || col >= sideLength) {
            throw new IndexOutOfBoundsException("row or col cannot surpass the size - 1!");
        }
        return coordinate[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row >= sideLength || col >= sideLength) {
            throw new IndexOutOfBoundsException("row or col cannot surpass the size - 1!");
        }
        /* using the new grid to avoid backwash. */
        if (isOpen(row, col) && secGrid.connected(transfer(row, col), secTopPoint)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        return grid.connected(topPoint, bottomPoint);
    }

    private int transfer(int row, int col) {
        return row * sideLength + col;
    }
}
