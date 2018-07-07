package com.github.merkle.md5

import com.github.merkle.{BlockView, MerkleTree}
import com.github.merkle.utils.DigestFunctions._
import com.github.merkle.utils.UnitSpec
import com.github.merkle.conversion.Conversion._

class MerkleTreeMd5Spec extends UnitSpec {

  it should "have equals root with plain hashes, string and bytes" in {
    val blocks: Seq[BlockView] = Seq(
      leaf("1", "MD5"),
      "c81e728d9d4c2f636f067f89cc14862c",
      "3".getBytes(),
      "4".getBytes()
    )

    val first = MerkleTree(blocks, md5Digest(_))

    first.rootHash should be("120c93a07a99bce0cb1eee82c4b6cc2e")
  }

  it should "have equals root with plain hashes" in {
    val blocks: Seq[BlockView] = Seq(
      "c4ca4238a0b923820dcc509a6f75849b",
      "c81e728d9d4c2f636f067f89cc14862c",
      "eccbc87e4b5ce2fe28308fd9f2a7baf3",
      "a87ff679a2f3e71d9181a67b7542122c"
    )

    val first = MerkleTree(blocks, md5Digest(_))

    first.rootHash should be("120c93a07a99bce0cb1eee82c4b6cc2e")
  }

  it should "have equals root nodes with plain hashes" in {
    val blocks: Seq[BlockView] = Seq(
      "4829182CAC1A47976743C58F435FBEB9",
      "64F92A0A36F0A2D82BD4831CDA67EEA3",
      "C43E5A6C225B4C7017869B64DCD8CC00",
      "2E60913F544CCDC368098AC6DD48F8A8"
    )

    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes" in {
    val blocks: Seq[BlockView] = Seq("1", "2", "3", "4")
    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: with odd leafs number" in {
    val blocks: Seq[BlockView] = Seq("AB", "RA", "CA", "DA", "BRA")
    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes: with odd leafs number" in {
    val firstInput: Seq[BlockView] = Seq("AB", "RA", "CA", "DA", "BRA")
    val secondInput: Seq[BlockView] = Seq("AB", "RA", "CA", "DA", "BRA!")
    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

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

    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes" in {
    val firstInput: Seq[BlockView] = Seq("1", "2", "3", "4")
    val secondInput: Seq[BlockView] = Seq("1", "2", "3", "5")
    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

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

    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

    getRootValue(first) should not be getRootValue(second)
  }

  it should "have equals right root nodes" in {
    val blocks: Seq[BlockView] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    first.right.get.hash.left.get should be(second.right.get.hash.left.get)
  }

  it should "have equals right root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    first.right.get.hash.left.get should be(second.right.get.hash.left.get)
  }

  it should "have different right root nodes" in {
    val firstInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

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

    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

    first.right.get.hash.left.get should not be second.right.get.hash.left.get
  }

  it should "have equals left root nodes" in {
    val blocks: Seq[BlockView] = Seq("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH")
    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    first.left.get.hash.left.get should be(second.left.get.hash.left.get)
  }

  it should "have equals left root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](0, 1, 2, 3),
      Array[Byte](4, 5, 5, 7),
      Array[Byte](8, 9, 10, 11),
      Array[Byte](12, 13, 14, 15)
    )

    val first = MerkleTree(blocks, md5Digest(_))
    val second = MerkleTree(blocks, md5Digest(_))

    first.left.get.hash.left.get should be(second.left.get.hash.left.get)
  }

  it should "have different left root nodes" in {
    val firstInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DD")
    val secondInput: Seq[BlockView] = Seq("AA", "BB", "CC", "DE")
    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

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

    val first = MerkleTree(firstInput, md5Digest(_))
    val second = MerkleTree(secondInput, md5Digest(_))

    first.left.get.hash.left.get should not be second.left.get.hash.left.get
  }

}
