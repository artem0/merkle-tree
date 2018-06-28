package com.github.merkle

import com.github.merkle.utils.DigestFunctions._
import com.github.merkle.utils.UnitSpec

class MerkleTreeSha1Spec extends UnitSpec {

  it should "have equals root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.hash should be(second.hash)
  }

  it should "have equals root nodes: with odd leafs number" in {
    val blocks: Seq[Array[Byte]] = Seq("AB", "RA", "CA", "DA", "BRA")
    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.hash should be(second.hash)
  }

  it should "have different root nodes: with odd leafs number" in {
    val firstInput: Seq[Array[Byte]] = Seq("AB", "RA", "CA", "DA", "BRA")
    val secondInput: Seq[Array[Byte]] = Seq("AB", "RA", "CA", "DA", "BRA!")
    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

    first.hash should not be second.hash
  }

  it should "have equals root nodes: input sequence of bytes with odd leafs number" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](55, 33, 22, 13),
      Array[Byte](42, 85, 45, 87),
      Array[Byte](83, 97, 10, 121),
      Array[Byte](25, 4, 104, 126),
      Array[Byte](28, 93, 54, 15)
    )

    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.hash should be(second.hash)
  }

  it should "have equals root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.hash should be(second.hash)
  }

  it should "have different root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val secondInput: Seq[Array[Byte]] = Seq("1", "2", "3", "5")
    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

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

    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

    first.hash should not be second.hash
  }

  it should "have equals right root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.right.get.hash should be(second.right.get.hash)
  }

  it should "have equals right root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.right.get.hash should be(second.right.get.hash)
  }

  it should "have different right root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

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

    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

    first.right.get.hash should not be second.right.get.hash
  }

  it should "have equals left root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.left.get.hash should be(second.left.get.hash)
  }

  it should "have equals left root nodes: input sequence of bytes" in {
    val blocks: Seq[Array[Byte]] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree.apply(blocks, sha1Digest(_))
    val second = MerkleTree.apply(blocks, sha1Digest(_))

    first.left.get.hash should be(second.left.get.hash)
  }

  it should "have different left root nodes" in {
    val firstInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[Array[Byte]] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

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

    val first = MerkleTree.apply(firstInput, sha1Digest(_))
    val second = MerkleTree.apply(secondInput, sha1Digest(_))

    first.left.get.hash should not be second.left.get.hash
  }

}
