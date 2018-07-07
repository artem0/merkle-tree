package com.github.merkle.sha1

import com.github.merkle.{BlockView, MerkleTree}
import com.github.merkle.utils.DigestFunctions._
import com.github.merkle.utils.UnitSpec
import com.github.merkle.conversion.Conversion._


class MerkleTreeSha1Spec extends UnitSpec {

  it should "have equals root nodes" in {
    val blocks: Seq[BlockView] = Seq(
      "70de60d2b3187d88ec32b10a6c0bb4ff49bf93b2",
      "1691b92ff0730332cd199d7a45c977738954440e",
      "84d5bc2c582af0940cc1fea10391711c80124125",
      "83a905fd45c503e2b01fa19386465f304ba1975e"
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: with odd leafs number" in {
    val blocks: Seq[BlockView] = Seq(
      "AB".getBytes(),
      "RA".getBytes(),
      "CA".getBytes(),
      "DA".getBytes(),
      "BRA".getBytes()
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes: with odd leafs number" in {
    val firstInput: Seq[BlockView] = Seq(
      "AB".getBytes(),
      "RA".getBytes(),
      "CA".getBytes(),
      "DA".getBytes(),
      "BRA".getBytes()
    )

    val secondInput: Seq[BlockView] = Seq(
      "AB".getBytes(),
      "RA".getBytes(),
      "CA".getBytes(),
      "DA".getBytes(),
      "BRA!".getBytes()
    )
    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    getRootValue(first) should not be getRootValue(second)
  }

  it should "have equals root nodes: input sequence of bytes with odd leafs number" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](55, 33, 22, 13),
      Array[Byte](42, 85, 45, 87),
      Array[Byte](83, 97, 10, 121),
      Array[Byte](25, 4, 104, 126),
      Array[Byte](28, 93, 54, 15)
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes" in {
    val firstInput: Seq[BlockView] = Seq(
      "1".getBytes(),
      "2".getBytes(),
      "3".getBytes(),
      "4".getBytes()
    )

    val secondInput: Seq[BlockView] = Seq(
      "1".getBytes(),
      "2".getBytes(),
      "3".getBytes(),
      "5".getBytes()
    )

    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    getRootValue(first) should not be getRootValue(second)
  }

  it should "have different root nodes: input sequence of bytes" in {
    val firstInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 16)
    )

    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    getRootValue(first) should not be getRootValue(second)
  }

  it should "have equals right root nodes" in {
    val blocks: Seq[BlockView] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    first.right.get.hash.left.get should be(second.right.get.hash.left.get)
  }

  it should "have equals right root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    first.right.get.hash.left.get should be(second.right.get.hash.left.get)
  }

  it should "have different right root nodes" in {
    val firstInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    first.right.get.hash.left.get should not be second.right.get.hash.left.get
  }

  it should "have different right root nodes: input sequence of bytes" in {
    val firstInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    first.right.get.hash.left.get should not be second.right.get.hash.left.get
  }

  it should "have equals left root nodes" in {
    val blocks: Seq[BlockView] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    first.left.get.hash.left.get should be(second.left.get.hash.left.get)
  }

  it should "have equals left root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, sha1Digest(_))
    val second = MerkleTree(blocks, sha1Digest(_))

    first.left.get.hash.left.get should be(second.left.get.hash.left.get)
  }

  it should "have different left root nodes" in {
    val firstInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    first.left.get.hash.left.get should not be second.left.get.hash.left.get
  }

  it should "have different left root nodes: input sequence of bytes" in {
    val firstInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val secondInput: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(firstInput, sha1Digest(_))
    val second = MerkleTree(secondInput, sha1Digest(_))

    first.left.get.hash.left.get should not be second.left.get.hash.left.get
  }

}
