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

  def apply(data: Seq[Block], digest: Digest): MerkleTree = {
    var trees = data.map(block => MerkleTree(digest(block)))

    while (trees.length > 1) {
      trees = trees.grouped(2).map { p =>
        val left = p(0)
        val right = if (p.length > 1) Option(p(1)) else None
        MerkleTree(hash = merge(digest, left.hash, right.map(_.hash)),
          left = Option(left), right)
      }.toSeq
    }
    trees.head
  }

  /**
    * Merge results for hashes of two blocks
    *
    * @param digest       target hash function for applying
    * @param left         left block
    * @param right        right block - Option on case for odd number of items, right is hanging leaf
    * @param stringDigest flag for handling in string/bytes[] form
    * @return resulted block
    */
  def merge(digest: Digest, left: Block, right: Option[Block],
            stringDigest: Option[Boolean] = Option(true)): Block = {
    if (stringDigest.getOrElse(false)) {
      val neighborHashesUnion = blockToHex(left ++ right.getOrElse(emptyByteArray))
      digest(neighborHashesUnion.getBytes())
    } else digest(left ++ right.getOrElse(emptyByteArray))
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

  /** Plain string to bytes array **/
  implicit def stringToBytesArray(string: String): Block = string.getBytes()


  /** The alias for an empty byte array**/
  private [this] val emptyByteArray = Array[Byte]()
}