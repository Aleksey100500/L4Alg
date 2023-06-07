public class RedBlackTree {
    private Node root; // тот элемент с которого начинается работа

    private boolean addNote(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNote(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;// Для новых нод всегда красный цвет
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) { //
                    boolean result = addNote(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNote(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }
    public boolean find(int value) { // Сем 4
        Node currentNode = root;
        while(currentNode != null) {
            if (currentNode.value == value) {
                return true;
            }
            if (value > currentNode.value) {
                currentNode = currentNode.rightChild;
            } else {
                currentNode = currentNode.leftChild;
            }
        }
        return false;
    }
    private void colorSwap(Node node) {  // смена цвета указанной ноды
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }
    private Node leftSwap(Node node) { // левосторонний перевод
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }
    private Node rightSwap(Node node) { // правосторонний перевод
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;

        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }
    private class Node {
        private int value; // значение которое храним в нашем узле
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
    }
    private enum Color {
        RED, BLACK
    }
}
