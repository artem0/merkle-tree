package com.github.merkle

import com.github.merkle.utils.DigestFunctions._
import com.github.merkle.utils.UnitSpec

class MerkleTreeMd5Spec extends UnitSpec {

  it should "have equals root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.hash should be(second.hash)
  }

  it should "have equals root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.hash should be(second.hash)
  }

  it should "have different root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val secondInput: Seq[Array[Byte]] = Seq("1", "2", "3", "5")
    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.hash should not be second.hash
  }

  it should "have different root nodes: input sequence of bytes" in {
    val firstInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 16)
    )

    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.hash should not be second.hash
  }

  it should "have equals right root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.right.get.hash should be(second.right.get.hash)
  }

  it should "have equals right root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.right.get.hash should be(second.right.get.hash)
  }

  it should "have different right root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.right.get.hash should not be second.right.get.hash
  }

  it should "have different right root nodes: input sequence of bytes" in {
    val firstInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.right.get.hash should not be second.right.get.hash
  }

  it should "have equals left root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.left.get.hash should be(second.left.get.hash)
  }

  it should "have equals left root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.left.get.hash should be(second.left.get.hash)
  }

  it should "have different left root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.left.get.hash should not be second.left.get.hash
  }

  it should "have different left root nodes: input sequence of bytes" in {
    val firstInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(firstInput, md5Digest(_))
    val second = MerkleTree.apply(secondInput, md5Digest(_))

    first.left.get.hash should not be second.left.get.hash
  }

}
