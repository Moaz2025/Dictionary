# Balanced_Binary_Search_Trees

## Description
Java dictionary implemented using two types of balanced binary search trees: AVL and Red-Black trees handling basic operations such as searching, insertion, deletion, batch inseration and batch deletion of items with ability to investigate the properity of tree structure.
CLI is implemented so that the user can interact with the dictionary and do the basic operations on it.
The type of tree is determined by the user at the start of application and each tree implements the same interface so that they are obligated to introduce the same set of operations but with each tree own implementation according to the logic of the used tree.
A GUI is a next step to be made using one of java user iterface libraries>

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
