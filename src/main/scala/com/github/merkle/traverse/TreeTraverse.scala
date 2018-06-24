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
        inorderRecursive(leftBranch.head)
        print(s" Root ${blockToHex(v)}")
        inorderRecursive(rightBranch.get)
    }
  }
}
