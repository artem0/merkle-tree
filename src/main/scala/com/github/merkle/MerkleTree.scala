package com.github.merkle

import java.security.MessageDigest

import com.github.merkle.MerkleTree.digestFunction

/**
  * Binary immutable tree of hashes
  *
  * @param hash  root hash value
  * @param left  left subtree
  * @param right right subtree
  */
case class MerkleTree(hash: BlockView,
                      left: Option[MerkleTree] = Option.empty,
                      right: Option[MerkleTree] = Option.empty){

  /** Human readable hash root **/
  def rootHash: String = MerkleTree.bytes2Hex(hash.left.get)
}

object MerkleTree {

  def apply(data: Seq[BlockView], digest: Digest): MerkleTree = {
    var trees = data.map {
      case Left(block) => MerkleTree(Left(digest(block)))
      case Right(hashedString) => MerkleTree(Left(hex2bytes(hashedString)))
    }

    while (trees.length > 1) {
      trees = trees.grouped(2).map { p =>
        val left = p(0)
        val right = if (p.length > 1) Option(p(1)) else None
        MerkleTree(Left(merge(digest, left.hash, right.map(_.hash))),
          left = Option(left), right)
      }.toSeq
    }
    trees.head
  }

  /** Apply for arbitrary digest funtion
    *
    * {{{
    *  val tree = MerkleTree.apply(blocks, "SHA-384")
    * }}}
  */
  def apply(data: Seq[BlockView], digestFunctionName: String): MerkleTree = {
    apply(data, digestFunction(digestFunctionName)(_))
  }

  /**
    * Merge results for hashes of two blocks
    *
    * @param digest       target hash function for applying
    * @param first         left block
    * @param second        right block - Option on case for odd number of items, right is hanging leaf
    * @return resulted block
    */
  def merge(digest: Digest, first: BlockView, second: Option[BlockView]): Block = {
    (first.isLeft, second.getOrElse(Right(emptyByteArray)).isLeft) match {
      case (true, true) => {
        val neighborHashesUnion = bytes2Hex(first.left.get ++ second.map(_.left.get).getOrElse(emptyByteArray))
        digest(neighborHashesUnion.getBytes())
      }
      case (true, false) => {
        if (second.isDefined) digest(bytes2Hex(first.left.get) + second.get.right.get)
        else digest(bytes2Hex(first.left.get))
      }
    }
  }

  /**
    * Applying message digest algorithm to byte sequences
    *
    * @param bytes        byte sequences
    * @param hashFunction hash function
    * @return digests
    */
  def digestFunction(hashFunction: String)(bytes: Array[Byte]): Block = {
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
    digestFunction(hashFunction)(string.getBytes("UTF-8"))
  }

  /**
    * Convert block to HEX represented string
    *
    * @param hash hash value
    * @return hash like HEX string
    */
  def bytes2Hex(hash: Block): String = hash.map("%02x".format(_)).mkString

  /**
    * Convert HEX represented string to byte sequences
    * @param hex input HEX string
    * @return byte sequences
    */
  def hex2bytes(hex: String): Array[Byte] = {
    hex.replaceAll("[^0-9A-Fa-f]", "")
      .sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)
  }

  /** Plain string to bytes array **/
  implicit def stringToBytesArray(string: String): Block = string.getBytes()


  /** The alias for an empty byte array**/
  private [this] val emptyByteArray = Array[Byte]()
}