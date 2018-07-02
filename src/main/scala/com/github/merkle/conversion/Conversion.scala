package com.github.merkle.conversion

import com.github.merkle.BlockView
import com.github.merkle.MerkleTree.{bytes2Hex, digestFunction}

/**
  * Implicits for leafs
  */
object Conversion {


  /** Plain string with specified hash unction Leaf **/
  implicit def leaf(plainString: String, hashFunction: String): BlockView = {
    Right(bytes2Hex(digestFunction(plainString, hashFunction)))
  }

  /** Sequence of bytes Leaf **/
  implicit def bytes2BlockView(bytes: Array[Byte]): BlockView = Left(bytes)

  /** Hash string Leaf **/
  implicit def hashString2BlockView(hashString2BlockView: String): BlockView = Right(hashString2BlockView)
}
