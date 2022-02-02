package TreeImplementation.BinaryTree.src;

public class TreeTraversal<E>{
    private static class Node<E> {
        E   data;
        Node<E> left;
        Node<E> right;

        public Node(E item){
            data = item;
            left = null;
            right = null;
        }
    }

    private Node<E> root = null;
    private boolean addReturn = false;
    private E deleteReturn = null;
    private int rightHeight = 0;
    private int leftHeight = 0;


    private void printGreaterThanInorder(Node<E> node, E target){
        if (node == null)
            return;
        printGreaterThanInorder(node.left, target);
        if ((Integer)node.data >= (Integer) target)
            System.out.print(node.data + " ");
        printGreaterThanInorder(node.right, target);
    }

    public void printGreaterThanInorder(E target){
        printGreaterThanInorder(root, target);
    }

    public Node<E> add(Node<E> localRoot, E item){
        if (localRoot == null){
            addReturn = true;
            return new Node<>(item);
        }else {
            int comp = ((Integer)item).compareTo((Integer) localRoot.data);
            if (comp == 0){
                addReturn = false;
                return localRoot;
            }else if (comp < 0){
                localRoot.left = add(localRoot.left, item);
                return localRoot;
            }else {
                localRoot.right = add(localRoot.right, item);
                return localRoot;
            }
        }
    }

    public boolean add(E item){
        root = add(root, item);
        return addReturn;
    }

    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }

        int compResult = ((Integer)item).compareTo((Integer) localRoot.data);
        if (compResult < 0) {
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.data = findPredessor(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findPredessor(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findPredessor(parent.right);
        }
    }

    public void printInorder(){
        printInorder(root);
    }

    public void printInorder(Node <E> node){
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }



    public E find(E data){
        return find(root, data);
    }

    public E find(Node<E> node, E data){
        if (node == null)
            return null;
        int comp = ((Integer) data).compareTo((Integer) node.data);
        if (comp == 0)
            return node.data;
        else if (comp < 0)
            return find(node.left, data);
        else
            return find(node.right, data);
    }

    public int countNodesInRange(int f, int l){
        return countNodesInRange(root, f, l);
    }

    public int countNodesInRange(Node<E> node, int f, int l){
        if (node == null || f >= l)
            return 0;
        if ((Integer)node.data >= f && (Integer)node.data <= l)
            return 1 + countNodesInRange(node.left, f, l) + countNodesInRange(node.right, f, l);
        else if ((Integer)node.data < f)
            return 0 + countNodesInRange(node.right, f, l);
        else
            return 0 + countNodesInRange(node.left, f, l);
    }

    private Node<E> parent = null;

    public Node<E> getParent(E item){
        return getParent(root, item);
    }

    public Node<E> getParent(Node<E> localRoot, E item){
        if (localRoot == null)
            return null;
        int comp = ((Integer) item).compareTo((Integer) localRoot.data);
        if (comp == 0){
            if (localRoot == root)
                return null;
            else
                return parent;
        }else if (comp < 0){
            parent = localRoot;
            return getParent(localRoot.left, item);
        }else {
            parent = localRoot;
            return getParent(localRoot.right, item);
        }
    }

    public boolean isSiblings(E item1, E item2){
        return (getParent(item1) == getParent(item2));
    }

    public int getDepth(E item){
        E current = item;
        int count = 0;
        while (current != null){
            Node<E> p = getParent(current);
            if (p != null){
                current = p.data;
                count++;
            }else
                break;
        }
        return count;
    }

    public static void main(String[] args){
        TreeTraversal tree = new TreeTraversal();


        tree.add(56);
        tree.add(46);
        tree.add(36);
        tree.add(66);
        tree.add(50);
        tree.add(32);
        tree.add(60);
        /*tree.add(70);
        tree.add(55);
        tree.add(59);
        tree.add(72);
        tree.add(69);*/

        tree.printInorder();
        System.out.println();
        //System.out.println(tree.countNodesInRange(tree.root, 10, 20));
        //System.out.println(tree.getParent(36).data);
        //System.out.println(tree.getParent(50).data);
        //System.out.println(tree.isSiblings(36, 50));

        System.out.println(tree.getDepth(46));
        /*System.out.println("count greater than 41 is: " + tree.countGreaterThan(41));
        System.out.print("the numbers which greater than 41: ");
        tree.printGreaterThanInorder(41);
        System.out.println();
        tree.delete(20);
        tree.printInorder();*/
    }
}
