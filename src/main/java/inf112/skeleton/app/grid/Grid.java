package inf112.skeleton.app.grid;

import java.util.Iterator;

public class Grid<E> implements IGrid<E> {
    @Override
    public E get(CellPosition pos) {
        return null;
    }
    // h

    @Override
    public void set(CellPosition pos, Object value) {

    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getCols() {
        return 0;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        return null;
    }
}
