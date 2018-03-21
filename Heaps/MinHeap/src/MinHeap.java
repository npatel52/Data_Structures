public class MinHeap {

    private int[] heap;
    private int size;
    private final int INITIAL_CAPACITY = 16;

    public MinHeap(){
        heap = new int[INITIAL_CAPACITY];
        size = 0;
    }

    public int size(){
        return this.size;
    }

    public int peek(){
        if(this.size == 0) throw new RuntimeException("Heap is empty()!");
        return this.heap[0];
    }

    private boolean hasParent(int childIndex){
        return childIndex != 0;
    }

    private boolean hasLeftChild(int parentIndex){
        return (getLeftChild(parentIndex)) < this.size;
    }

    private boolean hasRightChild(int parentIndex){
        return (getRightChild(parentIndex)) < this.size;
    }

    private int leftChild(int index){
        return this.heap[index];
    }

    private int rightChild(int index){
        return this.heap[index];
    }

    private int getParent(int childIndex){
        return (childIndex - 1)/2;
    }

    private int getLeftChild(int parentIndex){
        return 2 * parentIndex + 1;
    }

    private int getRightChild(int parentIndex){
        return 2 * parentIndex + 2;
    }

    private void swap(int child, int parent){
        int temp = this.heap[child];
        this.heap[child] = this.heap[parent];
        this.heap[parent] = temp;
    }

    private boolean hasCapacity(){
        return this.size < this.heap.length;
    }

    // Doubling capacity to hold all nodes that can be inserted into the next level
    private void increaseCapacity(){
        // double the size to accomodate the next level
        int[] tempHeap = new int[2 * this.size];
        // copy the conent of the current heap
        System.arraycopy(this.heap, 0, tempHeap, 0, this.size);
        // update heap
        this.heap = tempHeap;
    }

    public void insert(int value){
        if(!hasCapacity())
            increaseCapacity();
        // insert value to the heap
        this.heap[this.size] = value;
        // heapify to eliminate violations
        this.heapifyUp();
        // update heap size
        ++this.size;
    }

    public int poll(){
        if(this.size == 0) throw new RuntimeException("Heap is empty()!");
        // store the node which will be removed
        int min = this.heap[0];
        // replace root with the rightmost node
        this.heap[0] = this.heap[this.size - 1];
        // update the heap size
        --this.size;
        // fix violation of min heap
        this.heapifyDown();
        return min;
    }

    private void heapifyDown(){
        int parentIndex = 0;
        // if parent doesn't have left child then it definetely doesn't have right child
        while(hasLeftChild(parentIndex)) {
            // assuming left child will be smallest
            int smallerChildIndex = getLeftChild(parentIndex);
            // if right child exist the smallest among both gets precedence
            if(hasRightChild(parentIndex)){
                int rightChildIndex = getRightChild(parentIndex);
                if(leftChild(smallerChildIndex) > rightChild(rightChildIndex))
                    smallerChildIndex = rightChildIndex;
            }
            // check if violation exist
            if(this.heap[parentIndex] > this.heap[smallerChildIndex]){
                swap(parentIndex, smallerChildIndex);
                parentIndex = smallerChildIndex;
            }else{
                break;
            }
        }
    }

    private void heapifyUp(){
        int childIndex = this.size;
        int parentIndex = getParent(childIndex);

        // check for min heap violation (parent <= both left and right child)
        while(this.heap[parentIndex] > this.heap[childIndex]) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParent(childIndex);
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        int index = 0;
        result.append("[");
        while(index < this.size) {
            result.append(" ");
            result.append(this.heap[index]);
            ++index;
        }
        result.append(" ]");
        return result.toString();
    }
}

