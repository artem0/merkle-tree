package com.github.merkle.traverse

import com.github.merkle.MerkleTree
import com.github.merkle.MerkleTree.blockToHex

object TreeTraverse {

  /**
    * Inorder recursive traverse
    *
    * @param tree Merkle tree
    */
  def inorderRecursive(tree: MerkleTree) {
    tree match {
      case MerkleTree(v, None, None) => print(s" Leaf ${blockToHex(v)}")
      case MerkleTree(v, leftBranch, rightBranch) =>
        if (leftBranch.isDefined) inorderRecursive(leftBranch.head)
        print(s" Root ${blockToHex(v)}")
        if (rightBranch.isDefined) inorderRecursive(rightBranch.head)
    }
  }

  /**
    * Preorder recursive traverse
    *
    * @param tree Merkle tree
    */
  def preorderRecursive(tree: MerkleTree) {
    tree match {
      case MerkleTree(v, None, None) => print(s" Leaf ${blockToHex(v)}")
      case MerkleTree(v, leftBranch, rightBranch) =>
        print(s" Root ${blockToHex(v)}")
        if (leftBranch.isDefined) preorderRecursive(leftBranch.head)
        if (rightBranch.isDefined) inorderRecursive(rightBranch.head)
    }
  }

  /**
    * Postorder recursive traverse
    *
    * @param tree Merkle tree
    */
  def postorderRecursive(tree: MerkleTree) {
    tree match {
      case MerkleTree(v, None, None) => print(s" Leaf ${blockToHex(v)}")
      case MerkleTree(v, leftBranch, rightBranch) =>
        if (leftBranch.isDefined) postorderRecursive(leftBranch.head)
        if (rightBranch.isDefined) inorderRecursive(rightBranch.head)
        print(s" Root ${blockToHex(v)}")
    }
  }
}
