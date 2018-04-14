import java.util.LinkedList;
import java.util.Queue;

public class BalancedTree {

    private Node root;

    public void insert(int val){
        this.root = (this.root == null)? new Node(val) : doInsert(this.root, val);
    }


    // height of leaf node is zero
    private Node doInsert(Node root, int val){

        if(root == null) return new Node(val);

        Node parent = root;
        if(val >= parent.val) {
            parent.right = doInsert(root.right, val);
        }else {
            parent.left = doInsert(root.left, val);
        }

        // recursively update height after insertion
        parent.height = 1 + Math.max(getHeight(parent.left), getHeight(parent.right));
        System.out.println("BF: " + getBalanceFactor(parent));
        // check for violations
        if(hasViolation(parent)){// fix violation and return new parent
            parent = fixViolation(parent);
        }
        // recursively update height after insertion
        return parent;
    }


    private Node fixViolation(Node parent){
        int rootBF = getBalanceFactor(parent);
        int childBF = 0;
        // left heavy
        if(rootBF == 2){
            childBF = getBalanceFactor(parent.left);
            // CASE: LR (requires two rotations for balancing)
            if(childBF == -1){
                parent.left = leftRotate(parent.left, parent.left.right);
            }
            // CASE: LL
            parent = rightRotate(parent, parent.left);
        }else{ // right heavy
            childBF = getBalanceFactor(parent.right);
            // CASE: RL
            if(childBF == 1){
                parent.right = rightRotate(parent.right, parent.right.left);
            }
            // CASE: RR
            parent = leftRotate(parent, parent.right);
        }
        return parent;
    }


    // right rotate child node
    private Node rightRotate(Node parent, Node child){
        parent.left = child.right;
        child.right = parent;
        parent.height = 1 + Math.max(getHeight(parent.left), getHeight(parent.right));
        child.height = 1 + Math.max(getHeight(child.left), getHeight(child.right));
        return child;
    }

    private Node leftRotate(Node parent, Node child){
        parent.right = child.left;
        child.left = parent;
        parent.height = 1 + Math.max(getHeight(parent.left), getHeight(parent.right));
        child.height = 1 + Math.max(getHeight(child.left), getHeight(child.right));
        return child;
    }

    // Checks if the balance factor is within [-1, 1]
    private boolean hasViolation(Node root){
        return Math.abs(getBalanceFactor(root)) > 1;
    }

    private int getBalanceFactor(Node root){
        return getHeight(root.left) - getHeight(root.right);
    }

    private int getHeight(Node node){
        return (node == null)? -1 : node.height;
    }

    public void delete(int val){
        if(this.root == null) return;
        this.root = doDelete(this.root, val);
    }

    private Node doDelete(Node node, int val){
           if(node == null) return null;
           if(val > node.val){ // value on right sub tree
               node.right = doDelete(node.right, val);
           }else if(val < node.val){ // value on left subtree
               node.left = doDelete(node.left, val);
           }else{ // value found

               // Case 1: leaf node
               if(node.left == null && node.right == null){
                   return null;
               }else if(node.left == null){  // case 2 : node has right child only
                   return node.right;
               }else if(node.right == null){ // case 3 : node has left child only
                   return node.left;
               }else{                        // case 4 : node has two child (del rightmost)
                   // find right most child
                   if(root.left.right != null){
                       node.val = getRightMost(node.left);
                   }
                   // delete right most child
                   node.left = delRightMost(node.left);

               }
           }
            // update height after deletion
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            if(hasViolation(node)){
                node = fixViolation(node);
            }

        return node;
    }

    private  int getRightMost(Node node){
        while(node.right != null){
            node = node.right;
        }
        return node.val;
    }

    private Node delRightMost(Node node){

        if(node.right == null) return node.left;
        node.right = delRightMost(node.right);

        // update height after deletion
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        if(hasViolation(node)){
            node = fixViolation(node);
        }

        return node;
    }
    public Node getRoot(){
        return this.root;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Queue<Node> tree = new LinkedList<>();
        tree.add(this.root);
        int nodes = 1;
        int nodeCount = 0;
        sb.append("[ ");
        while(!tree.isEmpty()){

            sb.append(" ( ");
            while(nodes != 0){
                Node node = tree.poll();
                sb.append( node.val + ":" + node.height + " ");
                if(node.left != null){
                    tree.add(node.left);
                    ++nodeCount;
                }

                if(node.right != null){
                    tree.add(node.right);
                    ++nodeCount;
                }

                --nodes;
            }
            sb.append(")");

            nodes = nodeCount;
            nodeCount = 0;
        }

        sb.append(" ]");

        return sb.toString();
    }
}
