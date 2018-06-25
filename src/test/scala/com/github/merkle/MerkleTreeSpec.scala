package com.github.merkle

import com.github.merkle.utils.DigestFunctions._
import com.github.merkle.utils.UnitSpec

class MerkleTreeSpec extends UnitSpec {

  it should "have equals root nodes" in {
    val blocks: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val first = MerkleTree.apply(blocks, md5Digest(_))
    val second = MerkleTree.apply(blocks, md5Digest(_))

    first.hash should be(second.hash)
  }

  it should "have different root nodes" in {
    val firstBlocks: Seq[Array[Byte]] = Seq("1", "2", "3", "4")
    val secondBlocks: Seq[Array[Byte]] = Seq("1", "2", "3", "5")
    val first = MerkleTree.apply(firstBlocks, md5Digest(_))
    val second = MerkleTree.apply(secondBlocks, md5Digest(_))

    first.hash should not be second.hash
  }

}
