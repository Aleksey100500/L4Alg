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

    // Бинарное дерево
    //Бинарным деревом называют частный случай дерева, где все элементы
    //обязательно строго уникальны, каждый родитель имеет не более 2 детей, при этом
    //левый ребенок всегда меньше родителя, а правый – больше.
    //
    // Благодаря своим свойствам, бинарные деревья позволяют эффективно
    //выстраивать поисковые алгоритмы. Находясь на любом из узлов мы всегда точно
    // знаем куда именно нам нужно пойти – налево или направо – чтобы найти искомый
    //элемент. Алгоритм обхода бинарного дерева происходит в глубину и очень
    //напоминает алгоритм бинарного поиска.

    // Сбалансированное дерево
    //Сбалансированным деревом называют частный случай бинарного дерева, у
    //которого выполняется следующее требование: для любого узла дерева высота его
    //правого поддерева отличается от высоты левого поддерева не более чем на
    //единицу.
    //Таким образом, сбалансированное дерево дает нам идеальную структуру для
    //бинарного поиска – корень такого дерева — это его центральный элемент –
    //количество элементов справа и слева от него различается не более чем на
    //единицу, что характерно для выбора стартовой позиции в бинарном поиске. Таким
    //образом, сложность поиска по сбалансированному дереву составляет O(log n), что
    //дает очень высокую производительность.

    // Для поддержания свойства сбалансированности в процессе операций добавления
    //или удаления элементов такие деревья должны проводить операции балансировки,
    //чтобы не допустить разбалансированности и ухудшения сложности поиска.
    //Существуют различные типы сбалансированных деревьев – АВЛ-дерево,
    //красно-черное дерево, 2-3 дерево и т.д. Каждое из которых имеет собственные
    //алгоритмы балансировки. Так, например, для балансировки АВЛ-дерева каждая
    //нода помимо информации о значении и детях, так же хранит показатель глубины,
    //на основе которого проводится балансировка по специальному алгоритму. А у
    //красно-черного дерева вместо показателя глубины используется показатель цвета
    //– ноды могут быть либо черными, либо красными.
    //Использование сбалансированных деревьев позволяет создать динамическую
    //самоддерживаемую структуру, имеющую очень высокую скорость поиска
    //элементов.
}
