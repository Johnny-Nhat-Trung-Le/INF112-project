package inf112.skeleton.app.grid;

public interface IGrid<E> extends Iterable<E>{
    /**
     * Gets the value from the grid in the specified position.
     * @param pos position of cell in grid
     * @return value of Grid in the specified position
     */
    E get(CellPosition pos);

    /**
     * Sets the value of the cell in the specified position in the grid.
     * @param pos position of cell to be set
     * @param value new value of the cell
     */
    void set(CellPosition pos, E value);

    /**
     * @return number of rows in the grid
     */
    int getRows();

    /**
     * @return number of columns in the grid
     */
    int getCols();
}
