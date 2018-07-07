package com.github.merkle.arbitrary

import com.github.merkle.{BlockView, MerkleTree}
import com.github.merkle.utils.UnitSpec
import com.github.merkle.conversion.Conversion._

class MerkleTreeArbitraryDigestSpec extends UnitSpec {

  it should "have equals root nodes" in {
    val digest = "SHA-224"

    val blocks: Seq[BlockView] = Seq(
      leaf("Some", digest),
      leaf("Random", digest),
      leaf("String", digest)
    )

    val first = MerkleTree(blocks, digest)
    val second = MerkleTree(blocks, digest)

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes with different hashes" in {
    val digest = "SHA-224"
    val digest2 = "SHA-256"

    val blocks: Seq[BlockView] = Seq(
      leaf("Some", digest),
      leaf("Random", digest2),
      leaf("String", digest2)
    )

    val first = MerkleTree(blocks, digest)
    val second = MerkleTree(blocks, digest)

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: with odd leafs number" in {
    val digest = "SHA-256"

    val blocks: Seq[BlockView] = Seq(
      leaf("AB", digest), leaf("RA", digest),
      leaf("CA", digest),
      leaf("DA", digest),
      leaf("BRA", digest)
    )

    val second = MerkleTree(blocks, digest)
    val first = MerkleTree(blocks, digest)

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes: with odd leafs number" in {
    val digest = "SHA-384"

    val firstInput: Seq[BlockView] = Seq(
      leaf("AB11",digest),
      leaf("RA22", digest),
      leaf("CA33", digest),
      leaf("DA44", digest),
      leaf("BRA55", digest)
    )

    val secondInput: Seq[BlockView] = Seq(
      leaf("AB11", digest),
      leaf("RA22", digest),
      leaf("CA33", digest),
      leaf("DA44", digest),
      leaf("BRA55!!", digest)
    )

    val first = MerkleTree(firstInput, digest)
    val second = MerkleTree(secondInput, digest)

    getRootValue(first) should not be getRootValue(second)
  }

  it should "have equals root nodes: with different hashes" in {
    val digest = "SHA-384"
    val digest2 = "SHA-256"

    val firstInput: Seq[BlockView] = Seq(
      leaf("AB11",digest),
      leaf("RA22", digest),
      leaf("CA33", digest),
      leaf("DA44", digest2),
      leaf("BRA55", digest2)
    )

    val secondInput: Seq[BlockView] = Seq(
      leaf("AB11", digest),
      leaf("RA22", digest),
      leaf("CA33", digest),
      leaf("DA44", digest2),
      leaf("BRA55", digest2)
    )

    val first = MerkleTree(firstInput, digest)
    val second = MerkleTree(secondInput, digest)

    getRootValue(first) should be (getRootValue(second))
  }

  it should "have equals root nodes: input sequence of bytes with odd leafs number" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](59, 66, 1, 55),
      Array[Byte](42, 85, 49, 76),
      Array[Byte](34, -70, 10, 55),
      Array[Byte](5, 120, -104, 126),
      Array[Byte](81, 66, 4, 1)
    )

    val digest = "SHA-512"
    val first = MerkleTree(blocks, digest)
    val second = MerkleTree(blocks, digest)

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have equals root nodes: input sequence of bytes" in {
    val blocks: Seq[BlockView] = Seq(
      Array[Byte](44, 41, 42, 33),
      Array[Byte](14, 51, 51, 74),
      Array[Byte](33, 95, 50, 41),
      Array[Byte](-12, -122, 32, 44)
    )

    val digest = "SHA-512"
    val first = MerkleTree(blocks, digest)
    val second = MerkleTree(blocks, digest)

    getRootValue(first) should be(getRootValue(second))
  }

  it should "have different root nodes" in {
    val digest = "SHA-384"

    val firstInput: Seq[BlockView] = Seq(
      leaf("Ab", digest),
      leaf("ra", digest),
      leaf("ca", digest),
      leaf("da", digest),
      leaf("bra", digest)
    )

    val secondInput: Seq[BlockView] =  Seq(
      leaf("Ab", digest),
      leaf("ra", digest),
      leaf("ca", digest), leaf("da", digest),
      leaf("bra!", digest)
    )

    val first = MerkleTree(firstInput, digest)
    val second = MerkleTree(secondInput, digest)

    getRootValue(first) should not be getRootValue(second)
  }

}
