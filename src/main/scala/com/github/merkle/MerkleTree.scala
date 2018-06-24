package com.github.merkle

import java.security.MessageDigest

/**
  * Binary immutable tree of hashes
  *
  * @param hash  root hash value
  * @param left  left subtree
  * @param right right subtree
  */
case class MerkleTree(hash: Block,
                      left: Option[MerkleTree] = Option.empty,
                      right: Option[MerkleTree] = Option.empty)

object MerkleTree {

  /**
    * Merge results for hashes of two blocks
    *
    * @param digest       target hash function for applying
    * @param first        first block
    * @param second       second block
    * @param stringDigest flag for handling in string/bytes[] form
    * @return resulted block
    */
  def merge(digest: Digest, first: Block, second: Block,
            stringDigest: Option[Boolean] = Option(true)): Block = {
    if (!stringDigest.getOrElse(false)) {
      val neighborHashesUnion = blockToHex(first ++ second)
      digest(neighborHashesUnion.getBytes())
    } else digest(first ++ second)
  }

  /**
    * Applying message digest algorithm to byte sequences
    *
    * @param bytes        byte sequences
    * @param hashFunction hash function
    * @return digests
    */
  def digestFunction(bytes: Array[Byte], hashFunction: String): Block = {
    MessageDigest.getInstance(hashFunction).digest(bytes)
  }

  /**
    * Applying message digest algorithm to string value
    *
    * @param string       string value
    * @param hashFunction hash function
    * @return digests
    */
  def digestFunction(string: String, hashFunction: String): Block = {
    digestFunction(string.getBytes("UTF-8"), hashFunction)
  }

  /**
    * Convert block to HEX represented string
    *
    * @param hash hash value
    * @return hash like HEX string
    */
  def blockToHex(hash: Block): String = hash.map("%02x".format(_)).mkString

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