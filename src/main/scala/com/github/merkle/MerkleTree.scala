package com.github.merkle

/**
  * Binary immutable tree of hashes
  *
  * @param hash  root hash value
  * @param left  left subtree
  * @param right right subtree
  */
case class MerkleTree(hash: Block,
                      left: Option[MerkleTree] = Option.empty,
                      right: Option[MerkleTree] = Option.empty) {


}

object MerkleTree {

  def inorderRecursive(tree: MerkleTree) {
    tree match {
      case MerkleTree(v, None, None) => print(s" Leaf ${blockToHex(v)}")
      case MerkleTree(v, leftBranch, rightBranch) =>
        inorderRecursive(leftBranch.head)
        print(s" Root ${blockToHex(v)}")
        inorderRecursive(rightBranch.get)
    }
  }

  def blockToHex(hash: Block): String = hash.map("%02x".format(_)).mkString
}