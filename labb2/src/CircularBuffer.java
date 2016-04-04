import java.util.NoSuchElementException;

/**
 * Queue for ints implemented with a circular buffer.
 */
public class CircularBuffer {

    private int[] buffer;
    private int elements;
    private int maxSize;
    private int remove;


    
    /**
     * Creates a new circular buffer with a given size;
     * @param size The size of the buffer.
     * @throws IllegalArgumentException If the size is smaller than 1.
     */
    public CircularBuffer(int size) {

        this.maxSize = size;
        this.elements = 0;
        this.remove = 0;

        if(size < 1){
            throw new IllegalArgumentException("unsupported size");
        }
        buffer = new int[size];

    }

    /**
     * Adds an element to the buffer.
     * @param i The element to add.
     * @throws IllegalStateException If the buffer is full.
     */
    public void put(int i) {
        if(elements >= maxSize){
            throw new IllegalStateException("full");
        }

        this.buffer[elements] = i;
        elements++;
    }

    /**
     * Returns and removes the first element in the buffer.
     * @return The first element in the buffer.
     * @throws NoSuchElementException If the buffer is empty.
     */
    public int take() {

        if(elements < 1){
            throw new NoSuchElementException("empty");
        }
        if(remove < elements){
            int temp = this.buffer[remove % maxSize];
            this.buffer[remove % maxSize] = this.buffer[elements - 1];
            remove++;
            elements--;
            return temp;
        }
        remove--;
        elements--;
        return this.buffer[elements];

    }

    /**
     * @return The current number of elements in the queue.
     */
    public int size() {

        return elements;
    }

    /**
     * @return The maximum number of elements in the queue.
     */
    public int maxSize() {

        return maxSize;
    }
}
