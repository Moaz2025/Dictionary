# Dictionary

## Description
Java dictionary implemented using two types of balanced binary search trees: AVL and Red-Black trees handling basic operations such as searching, insertion, deletion, batch insertion and batch deletion of items with ability to investigate properties of tree structure.
CLI is implemented so that the user can interact with the dictionary and do the basic operations on it.
The type of tree is determined by the user at the start of application and each tree implements the same interface so that they are obligated to introduce the same set of operations but with each tree own implementation according to the logic of the used tree.
A GUI is a next step to be made using one of java user iterface libraries.

## Definitions
### AVL Tree
An AVL (Adelson-Velskii and Landis) tree is a binary search tree with a balance condition.
The balance condition is easy to maintain, and it ensures that the depth of the tree is O(log
n). An AVL tree is identical to a binary search tree, except that for every node in the tree, the
height of the left and right subtrees can differ by at most 1. Balancing information is kept for
each node (in the node structure).
All the tree operations can be performed in O(log n) time. The reason that insertions and
deletions are potentially difficult is that the operation could violate the AVL tree property.
The balance of an AVL tree can be maintained with a simple modification to the tree, known
as a rotation.

### Red-Black Tree
A red black tree is a kind of self-balancing binary search tree in computer science. Each node of
the binary tree has an extra bit, and that bit is often interpreted as the color (red or black) of
the node. These color bits are used to ensure the tree remains approximately balanced during
insertions and deletions. Balance is preserved by painting each node of the tree with one of two
colors in a way that satisfies certain properties, which collectively constrain how unbalanced
the tree can become in the worst case. When the tree is modified, the new tree is subsequently
rearranged (using rotation) and repainted to restore the coloring properties. The properties are
designed in such a way that this rearranging and recoloring can be performed efficiently.

## Methods
Java interface defines standard Binary Balanced Search Tree (BBST) data structure that stores elements of generic type to allow flexibilty considering the used data type. The interface provides the following set of methods:

`boolean search(T key)`: This method takes a key of generic type as input and returns a boolean value indicating whether the key is present in the BBST or not. If the key is present, it returns true, otherwise false.

`boolean insert(T key)`: This method takes a key of generic type as input and inserts it into the BBST if it is not already present. If the key is inserted successfully, it returns true, otherwise false.

`boolean delete(T key)`: This method takes a key of generic type as input and deletes it from the BBST if it is present. If the key is deleted successfully, it returns true, otherwise false.

`long size()`: This method returns the number of nodes currently stored in the BBST.

`long height()`: This method returns the height of the BBST, which is the maximum depth of the tree from the root node to the deepest leaf node.

`void clear()`: This method removes all elements from the BBST, leaving an empty tree.

`void traverse()`: This method traverses the BBST and visits each node in a specific order. The order in which the nodes are visited is not specified by the interface and depends on the required implementation of traversal.

## Testing
Tests are done using JUnit5 covering the common and edge cases in both implementations of BBST: AVL and Red-Black trees, testing different user scenarios and evaluating each method in the tree individually.

## References

BBST and AVL tree:

  * https://www.programiz.com/dsa/avl-tree
  * https://youtu.be/Qmt0QwzEmh0

Red-Black tree:

Generics:

  * https://www.geeksforgeeks.org/generics-in-java/
  * https://youtu.be/K1iu1kXkVoA

JUnit:

  * https://youtu.be/vZm0lHciFsQ
