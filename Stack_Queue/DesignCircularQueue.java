// 
// Author: 
// Date  : January 4, 2020

package leetcode;

class DesignCircularQueue {

	// 
	// Author: @liaison and @andvary + kei
	// Date  : January 4, 2020
	private ListNode head, tail;
	private int count;
	private int capacity;
	
	public DesignCircularQueue() {
		
	}

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public DesignCircularQueue(int k) {
		this.capacity = k;
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if (count == capacity) {
			return false;
		}

		ListNode newNode = new ListNode(value);
		if (count == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		count++;
		
		return true;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if (this.count == 0) {
			return false;
		}
		
		// Delete the head node. 
		head = head.next;
		count--;
		
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if (count == 0) {
			return -1;
		}
		
		return head.val;
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if (count == 0) {
			return -1;
		}
		
		return tail.val;
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return count == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return count == capacity;
	}	


	/**
	 * Your MyCircularQueue object will be instantiated and called as such:
	 * MyCircularQueue obj = new MyCircularQueue(k);
	 * boolean param_1 = obj.enQueue(value);
	 * boolean param_2 = obj.deQueue();
	 * int param_3 = obj.Front();
	 * int param_4 = obj.Rear();
	 * boolean param_5 = obj.isEmpty();
	 * boolean param_6 = obj.isFull();
	 */





	// For testing. 
	public static void main(String[] args) {
		DesignCircularQueue solution = new DesignCircularQueue();

		// Test arguments. 
		// int num = 24;
		// int target = 2;
		// solution.getInt(num, target);



	}

}


// Array version. 
class MyCircularQueue {
    
    private int[] data;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        
        tail = (tail + 1) % size;
        data[tail] = value;
        
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        // Only one elem.   
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        
        head = (head + 1) % size;
        
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        
        return data[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        
        return data[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
    	// If the next elem to the tail is head, then the array is full. 
        return ((tail + 1) % size) == head;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */











